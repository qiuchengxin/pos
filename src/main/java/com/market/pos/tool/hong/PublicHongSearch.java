package com.market.pos.tool.hong;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.connect.JdbcHong;
import com.market.pos.tool.pk.GetQid;

public class PublicHongSearch {

    public static String  HongSearch(String qqid,String msg,String groupid){

        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);
        askQQMessage.setQQID(qqid);
        String ask = null;

        GetQid.getType(msg);
        String type = GetQid.type;

        String sql = "select content from hong where userid = 'admin' and type = " +
                "'" + type + "'";
        JdbcHong.searchHong(sql,"pay_data");
        String content = JdbcHong.content;
        System.out.println(sql);

        if (content == null){
            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 公用宏无此资源。");
            ask = new Gson().toJson(askQQMessage);
        }else {
            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 公用宏如下（建议您去网站上传私用宏）： " +
                    "\n" + content);
            ask = new Gson().toJson(askQQMessage);
        }
        return ask;
    }
}
