package com.market.pos.tool.serverOpenSearch;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.connect.JdbcServerOpenSearch;
import org.apache.commons.net.telnet.TelnetClient;

public class ServerOpenSearch {
    private static final int port = 3724;

    public static String openSearch(String hostIP,int port) {
        System.out.println(hostIP + "/" + port);
        try {
            //指明Telnet终端类型，否则会返回来的数据中文会乱码
            TelnetClient telnetClient = new TelnetClient("vt200");
            //socket延迟时间：5000ms
            telnetClient.setDefaultTimeout(5000);
            //建立一个连接
            telnetClient.connect(hostIP,port);
            //断开连接
            telnetClient.disconnect();
            return "ON";
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            return "OFF";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return "OFF";
        }
    }

    public static String selectType(String hostName){
        //查询服务器IP
        String type = null;
        String sql = "select host_ip from servers_ip where host_name like '%" + hostName + "%'";
        String host_ip = JdbcServerOpenSearch.searchHostIP(sql,"pay_data");
        if (host_ip.equals("UNKNOW")){
            type = "ERROR";
        }else {
            type = openSearch(host_ip,port);
        }
        return type;
    }

    public static String serverOpenSearchAnswers(String msg,String groupid){
        //获取用户目标服务器名称
        String msg_start = "开服查询";
        Pattern pattern = Pattern.compile(msg_start);
        Matcher matcher = pattern.matcher(msg);
        String hostName = matcher.replaceAll("").trim().replaceAll(" ","");
        //获取该服务器当前状态
        String type = selectType(hostName);
        String ask = null;
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);
        if (type.equals("ERROR")){
            askQQMessage.setMsg("未找到您所查询的服务器，请检查后再行查询！");
            ask = new Gson().toJson(askQQMessage);
        }else if (type.equals("ON")){
            askQQMessage.setMsg("服务器： " + hostName + "  状态： 已开服！");
            ask = new Gson().toJson(askQQMessage);
        }else if (type.equals("OFF")){
            askQQMessage.setMsg("服务器： " + hostName + "  状态： 未开服，请再等等吧~");
            ask = new Gson().toJson(askQQMessage);
        }else {
            askQQMessage.setMsg("未知错误，让我爹来捞我。");
            ask = new Gson().toJson(askQQMessage);
        }
        return ask;
    }
}
