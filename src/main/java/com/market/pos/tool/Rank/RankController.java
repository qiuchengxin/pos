package com.market.pos.tool.Rank;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.qiandao.DengJi;

public class RankController {
    public static String ask;

    public static void rankAll(String qqid,String groupid){
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);
        askQQMessage.setQQID(qqid);

        RankService.selectAllRank(groupid);
        String first_username = RankService.first_username;
        int first_grade = RankService.first_grade;
        DengJi.dengJi(first_grade);
        String firstDengji = DengJi.dengji;

        String second_username = RankService.second_username;
        int second_grade = RankService.second_grade;
        DengJi.dengJi(second_grade);
        String secondDengji = DengJi.dengji;

        String third_username = RankService.third_username;
        int third_grade = RankService.third_grade;
        DengJi.dengJi(third_grade);
        String thirdDengji = DengJi.dengji;

        RankService.selectRankByUserid(qqid,groupid);
        int myRank = RankService.myRowNo;

        askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] " +
                "\n第一名： " + first_username + " 修为： " + first_grade + " 境界： " + firstDengji + "" +
                "\n第二名： " + second_username + " 修为： " + second_grade + " 境界： " + secondDengji + "" +
                "\n第三名： " + third_username + " 修为： " + third_grade + " 境界： " + thirdDengji + "" +
                "\n您的排名是 ：第 " + myRank + " 位 ！" );
        ask = new Gson().toJson(askQQMessage);
    }
}
