package com.market.pos.tool.tuanQue;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.connect.JdbcTest;

public class TuanQue {

    public static String tuanQue(String msg,String userid){

        //组装qq回复语句
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("106");
        askQQMessage.setQQID(userid);
        if (msg.equals("1")){
            askQQMessage.setMsg("[CQ:face,id=66]确认成功！");
            String sql = "UPDATE target_type SET type = '1' where userid = " + userid;
            JdbcTest.insertTest(sql,"pay_data");
        }else if (msg.equals("2")){
            askQQMessage.setMsg("[CQ:face,id=67]拒绝成功！团长将会为此职业安排替补。");
            String sql = "UPDATE target_type SET type = '2' where userid = " + userid;
            JdbcTest.insertTest(sql,"pay_data");
        }
        String ask = new Gson().toJson(askQQMessage);
        return ask;
    }
}
