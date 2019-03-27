package com.market.pos.tool.qiyu;

import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.common.GetEncoding;
import com.market.pos.tool.connect.JdbcServerOpenSearch;
import com.market.pos.tool.lemoc.LemocWebSocketClient;
import org.apache.log4j.Logger;

public class QiYUController {

    private static org.apache.log4j.Logger logger = Logger.getLogger(QiYUController.class);

    public static final String POST_URL = "http://www.yayaquanzhidao.com/GetCD.ashx";

    /**
     * 访问鸭鸭全知道接口，获取奇遇CD
     * @return
     * @param  ID
     * @throws IOException
     */
    public static String Post(String ID) throws IOException {
        //请求的webservice的url
        URL url = new URL(POST_URL);
        //创建http链接
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        //设置请求的方法类型
        httpURLConnection.setRequestMethod("POST");
        //设置请求的内容类型
        httpURLConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
        //设置发送数据
        httpURLConnection.setDoOutput(true);
        //设置接受数据
        httpURLConnection.setDoInput(true);
        //发送数据,使用输出流
        OutputStream outputStream = httpURLConnection.getOutputStream();
        //发送的soap协议的数据
        String content = "FID="+ URLEncoder.encode(ID, "UTF-8");
        //发送数据
        outputStream.write(content.getBytes());
        //接收数据
        InputStream inputStream = httpURLConnection.getInputStream();
        //定义字节数组
        byte[] b = new byte[1024];
        //定义一个输出流存储接收到的数据
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //开始接收数据
        int len = 0;
        while (true) {
            len = inputStream.read(b);
            if (len == -1) {
                //数据读完
                break;
            }
            byteArrayOutputStream.write(b, 0, len);
        }
        //从输出流中获取读取到数据(服务端返回的)
        String response = byteArrayOutputStream.toString();
//        String response = new String(byteArrayOutputStream.toString().getBytes("GB2312"),"utf-8");
        logger.info("response -- " + GetEncoding.getEncoding(response));
        return response;
    }

    /**
     * 根据用户用意区分答复不同的奇遇内容
     * @param response
     * @param type
     * @return
     */
    public static String searchByType(String response,int type){
        //获取的接口返回数组
        JSONArray jsonArray = JSONArray.parseArray(response);
        //buff = 0 ，进入CD的集合
        JSONArray arrayOFF = new JSONArray();
        //buff = 1/2，可以查看的集合
        JSONArray arrayON = new JSONArray();
        //buff = 3 ，失去联系的集合
        JSONArray arrayNotFound = new JSONArray();
        //将从接口获取的数据进行分类
        for (int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String buff = jsonObject.getString("Buff");
            String msg = jsonObject.getString("Msg");
            logger.info("msg -- " + GetEncoding.getEncoding(msg));

            if (buff.equals("0")){
                JSONObject OFF = new JSONObject();
                OFF.put("Msg",msg);
                arrayOFF.add(OFF);
            }else if (buff.equals("1") || buff.equals("2")){
                JSONObject ON = new JSONObject();
                ON.put("Msg",msg);
                arrayON.add(ON);
            }else {
                JSONObject NotFound = new JSONObject();
                NotFound.put("Msg",msg);
                arrayNotFound.add(NotFound);
            }
        }

        //根据用户的用意，提供不同的回答
        String arrayReturn = null;
        if (type == 0){//查询进入CD的列表
            arrayReturn = arrayOFF.toJSONString();
        }else if (type == 1){
            arrayReturn = arrayON.toJSONString();
        }else {
            arrayReturn = arrayNotFound.toJSONString();
        }
        logger.info("arrayReturn -- " + GetEncoding.getEncoding(arrayReturn));
        return arrayReturn;
    }

    /**
     * 获得答复
     * @param msg
     * @param type
     * @param groupid
     * @return
     * @throws IOException
     */
    public static String qyAnswers(String msg,int type,String groupid,String msg_start,String ans_start) throws IOException {
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);
        String ask = null;
        //获取用户目标服务器名称
        if (msg.matches("cd宠物查询.*")){
           msg_start = "cd宠物查询";
        }
        Pattern pattern = Pattern.compile(msg_start);
        Matcher matcher = pattern.matcher(msg);
        String hostName = matcher.replaceAll("").trim().replaceAll(" ","");
        //通过hostName查询ID,获得接口返回
        String sql = "select qy_id from servers_ip where host_name like '%" + hostName + "%'";
        String ID = JdbcServerOpenSearch.searchQyId(sql,"pay_data");
        if (ID.equals("0")){
            askQQMessage.setMsg("您查询的区服名称我还不认识哦~");
            ask = new Gson().toJson(askQQMessage);
            logger.info("输入的文本编码" + GetEncoding.getEncoding("您查询的区服名称我还不认识哦~"));
        }else {
            String response = Post(ID);
            logger.info("response // ans -- " + GetEncoding.getEncoding(response));

            //查询type = 0/1/2的奇遇
            String arrayReturn = searchByType(response, type);
            logger.info("arrayReturn // ans -- " + GetEncoding.getEncoding(arrayReturn));

            JSONArray jsonArray = JSONArray.parseArray(arrayReturn);
            //初始化答复
            String answer = "【服务器】：" + hostName + "\n";
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = i + 1;
                String ans = jsonObject.getString("Msg");
                logger.info("ans -- " + GetEncoding.getEncoding(ans));
                String Ianswer = "[" + id + "] " + ans;
                answer =answer + Ianswer;
                logger.info("answer -- " + GetEncoding.getEncoding(answer));
            }
            askQQMessage.setMsg(ans_start + answer);
            ask = new Gson().toJson(askQQMessage);
        }
        return ask;
    }
}
