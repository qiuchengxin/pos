package com.market.pos.tool.qiandao;import com.google.gson.Gson;import com.market.pos.pojo.AskQQMessage;public class DianZan {    public static String ask ;    public static String ask_dianzhan ;    public static void dianZan(String qqid,String groupid){        AskQQMessage askQQMessage = new AskQQMessage();        askQQMessage.setAct("101");        askQQMessage.setQQID(qqid);        askQQMessage.setGroupid(groupid);        askQQMessage.setMsg("[CQ:at,qq=" +qqid+ "]点赞成功!");        ask = new Gson().toJson(askQQMessage);        AskQQMessage askQQMessage_dianzhan = new AskQQMessage();        askQQMessage_dianzhan.setAct("110");        askQQMessage_dianzhan.setQQID(qqid);        ask_dianzhan = new Gson().toJson(askQQMessage_dianzhan);    }}