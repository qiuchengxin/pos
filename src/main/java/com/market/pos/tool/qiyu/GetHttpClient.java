package com.market.pos.tool.qiyu;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.common.GetEncoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetHttpClient {

    private static final String Url = "http://www.jx3qy.cn/home/qyinfo";

    public static String Get(String m,String R,String S,String t,String n,String u) {
        try {

            // 传入参数
            String realUrl = Url +
                    "?m=" + m +
                    "&R=" + R +
                    "&S=" + S +
                    "&t=" + t +
                    "&n=" + n +
                    "&csrf=" + csrf +
                    "&u=" + u;
            System.out.println(realUrl);
            URL url = new URL(realUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 在连接之前设置属性

            // Content-Type实体头用于向接收方指示实体的介质类型，指定HEAD方法送到接收方的实体介质类型，或GET方法发送的请求介质类型
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setRequestProperty("Cookie", "PHPSESSID=kpjhoptq5siucrfsupjr1dqaoq");
            // 设置打开与此URLConnection引用的资源的通信链接时使用的指定超时值（以毫秒为单位）
            conn.setConnectTimeout(10000);
            // 将读取超时设置为指定的超时时间，以毫秒为单位。
            // conn.setReadTimeout(60000);
            conn.setRequestMethod("GET");
            // Post 请求不能使用缓存
            conn.setUseCaches(false);

            // 建立连接
            conn.connect();
            // 获取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String result = "";
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            reader.close();
            conn.disconnect();
            return result;
        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    private static final String csrf = "1553672691188";
    public static String myQiYu(String msg){
        String m = "";
        String R = "";
        String S = "";
        String t = "";
        String n = "";
        String u = "";
        if (msg.matches("我的奇遇.*")){
            String msg_start = "我的奇遇";
            Pattern pattern = Pattern.compile(msg_start);
            Matcher matcher = pattern.matcher(msg);
            m = "1";
            R = "电信五区";
            S = "幽月轮";
            if (msg.equals("我的奇遇")){
                n = "UNKNOW";
            }else {
                n = matcher.replaceAll("").trim().replaceAll(" ","");
            }
        }else if (msg.matches("绝世奇遇.*")){
            String msg_start = "绝世奇遇";
            Pattern pattern = Pattern.compile(msg_start);
            Matcher matcher = pattern.matcher(msg);
            m = "1";
            R = "电信五区";
            S = "幽月轮";
            t = "绝世奇遇";
            u = matcher.replaceAll("").trim().replaceAll(" ","");
        }else if (msg.matches("所有绝世奇遇")){
            m = "1";
            R = "电信五区";
            S = "幽月轮";
            t = "绝世奇遇";
            u = "不限";
        }
        String info = Get(m,R,S,t,n,u);
        return info;
    }

    public static String getAsk(String msg,String groupid){
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);
        String ask = null;
        String answer = "^ o ^ 防止刷屏，至多只取五条数据哦！\n";

        String jsonString = myQiYu(msg);
        System.out.println(jsonString);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        String arrayString = jsonObject.getString("result");
        JSONArray jsonArray = JSONArray.parseArray(arrayString);
        if (jsonArray.size() == 0 ){
            askQQMessage.setMsg("无数据，请检查名称是否正确！");
            ask = new Gson().toJson(askQQMessage);
        }else {
            System.out.println(jsonArray.toJSONString());
            int k = jsonArray.size();
            if (k > 5){
                k = 5;
            }else if (k < 5){
                k = jsonArray.size();
            }
            for (int i = 0;i < k;i++){
                JSONObject jb = jsonArray.getJSONObject(i);
                String name = jb.getString("name");
                String serendipity = jb.getString("serendipity");
                String st = jb.getString("time");
                long lt = Long.valueOf(st);
                //转换为所需日期格式
                String dateString = GetEncoding.secondToDate(lt,"yyyy-MM-dd hh:mm:ss");
                answer = answer + "【" + name + "】 在  " + dateString + "  触发奇遇 " +  serendipity + "\n";
            }
            askQQMessage.setMsg(answer);
            ask = new Gson().toJson(askQQMessage);
        }
        return ask;
    }
}
