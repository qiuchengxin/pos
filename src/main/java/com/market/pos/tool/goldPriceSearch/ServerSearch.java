package com.market.pos.tool.goldPriceSearch;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.connect.JdbcServerUrl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerSearch {

    public static String sercerSearch(String msg,String groupid){
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);

        String database = "pay_data";
        String msg_start = ".*金价查询";
        Pattern pattern = Pattern.compile(msg_start);
        Matcher matcher = pattern.matcher(msg);
        String serverName = matcher.replaceAll("").trim().replaceAll(" ","");

        String sql = "SELECT * FROM `servers` where server_name = " + "'" + serverName + "'";
        System.out.println(sql);
        JdbcServerUrl.searchServerUrl(sql,database);
        String serverUrl = JdbcServerUrl.serverUrl;
        String ask = null;
        if (serverUrl != null){
            String money = GoldPriceSearch.htmlFiter(serverUrl);
            askQQMessage.setMsg( serverName + " 实时金价为：" + money + " ！" +
                    "\n数据取自：www.dd373.com ");
            ask = new Gson().toJson(askQQMessage);
        }else if (serverUrl == null){
            askQQMessage.setMsg("输入的服务器名称有误，请输入官方名称，不支持简称");
            ask = new Gson().toJson(askQQMessage);
        }
        return ask;
    }
}
