package com.market.pos.tool.hong;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.connect.JdbcHong;
import com.market.pos.tool.pk.GetQid;

public class HongSearch {

    public static String  HongSearch(String qqid,String msg,String groupid){

        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);
        askQQMessage.setQQID(qqid);
        String ask = null;

        GetQid.getPrivateType(msg);
        String type = GetQid.private_type;

        String sql = "select content from hong where userid = " +
                "'" + qqid + "'" + " and type = " +
                "'" + type + "'";
        JdbcHong.searchHong(sql,"pay_data");
        String content = JdbcHong.content;
        System.out.println(sql);

        if (content == null){
            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 您还没有录入" + type + "宏，请去网站上上传一下吧。");
            ask = new Gson().toJson(askQQMessage);
        }else {
            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 您所上传的" + type + "宏如下：" +
                    "\n" + content);
            ask = new Gson().toJson(askQQMessage);
        }
        return ask;
    }
}
