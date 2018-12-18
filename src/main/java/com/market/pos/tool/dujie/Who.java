package com.market.pos.tool.dujie;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.pk.GetGrade;
import com.market.pos.tool.pk.GetQid;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Who {

    public static String ask;

    public static void who(String userid,String msg,String groupid) {
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);

        //获取更新时间 update_time
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String update_time = df.format(new Date());

        GetQid.getQid(msg);
        String heler_userid_string = GetQid.ch_qqid;
        SkyService.searchSky(userid, groupid);
        String who = SkyService.who;
        int fail = SkyService.fail;

        GetGrade.getGrade(userid, groupid);
        int grade = GetGrade.grade;
        if (grade == 20000) {
            if (fail == 1) {
                askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 今天渡劫次数用完了，明天再来吧 ！");
                ask = new Gson().toJson(askQQMessage);
            } else if (fail != 1) {
                if (msg.matches(".*没有破坏者.*")) {
                    if (who.matches("nobody")) {
                        //猜对了
                        askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 渡劫成功，恭迎斗帝大人！");
                        ask = new Gson().toJson(askQQMessage);
                        SkyService.updateSkyDone(userid, groupid);
                        SkyService.updateFail(userid, groupid);
                        GetGrade.updateUsers(userid, 20001, update_time, groupid);
                    } else if (!who.matches("nobody")) {
                        //猜错了
                        askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 渡劫失败！");
                        ask = new Gson().toJson(askQQMessage);
                        SkyService.updateFail(userid, groupid);
                    }
                } else if (heler_userid_string.matches(who)) {
                    //猜对了
                    askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 渡劫成功，恭迎斗帝大人！");
                    ask = new Gson().toJson(askQQMessage);
                    SkyService.updateSkyDone(userid, groupid);
                    SkyService.updateFail(userid, groupid);
                    GetGrade.updateUsers(userid, 20001, update_time, groupid);
                } else if (!heler_userid_string.matches(who)) {
                    //猜错了
                    askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 渡劫失败！");
                    ask = new Gson().toJson(askQQMessage);
                    SkyService.updateSkyDone(userid, groupid);
                    SkyService.updateFail(userid, groupid);
                }
            }
        }else {
            askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 您不满足渡劫条件 ！");
            ask = new Gson().toJson(askQQMessage);
        }
    }
}
