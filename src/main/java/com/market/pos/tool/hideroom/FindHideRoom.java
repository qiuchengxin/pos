package com.market.pos.tool.hideroom;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FindHideRoom {
    public static String ask;

    public static void findHideRoom(String groupid){

        //获取当前查询时间
        Date date = new Date();
        long time_now = date.getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String time = df.format(time_now);

        hideRoomService.findHideRoom(time_now,groupid);
        int nowInHide = hideRoomService.nowInHide;
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);
        askQQMessage.setMsg("老夫掐指一算，算出当前正处于闭关状态的一共有 " + nowInHide +" 人 ！猜猜都有谁呢 ！");
        ask = new Gson().toJson(askQQMessage);
    }
}
