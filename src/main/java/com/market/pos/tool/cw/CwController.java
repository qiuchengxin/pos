package com.market.pos.tool.cw;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.findTreasure.BackpackService;
import com.market.pos.tool.pk.GetGrade;

/**
 * 做橙武
 */
public class CwController {
    public static String ask;

    public static void makeCw(String userid,String groupid){
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);
        //判定包里有无此物品
        BackpackService.searchBackPack(userid,groupid);
        String special = BackpackService.special;

        if (special == null){
            //包里没有玄晶
            askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 您的包里并没有玄晶哦 ！");
            ask = new Gson().toJson(askQQMessage);
        }else {
            //包里有玄晶
            GetGrade.getGrade(userid, groupid);
            int grade = GetGrade.grade;
            if (grade<=20000){
                //低于斗帝等级做cw
                CwService.insertCw(userid, groupid);
                askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 请阁下自己设计一把CW，想好了对我说：“我要锻造xxx” ,xxx为橙武名称 ！");
                ask = new Gson().toJson(askQQMessage);
            }else {
                //斗帝做CW
                CwService.insertCw(userid, groupid);
                CwService.updateIsDoudi(userid,groupid);
                askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 请阁下自己设计一把CW，想好了对我说：“我要锻造xxx” ,xxx为橙武名称 ！");
                ask = new Gson().toJson(askQQMessage);
            }
        }
    }
}
