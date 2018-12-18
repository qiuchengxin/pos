package com.market.pos.tool.dujie;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.connect.JdbcUsers;

public class HelpDuJie {
    //我(helper_userid)要帮助userid
    public static String ask;

    public static void helpDuJie(String helper_userid,String userid,String groupid){
        SkyService.searchSky(userid,groupid);
        String one = SkyService.one;
        String two = SkyService.two;
        String three = SkyService.three;

        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("106");
        askQQMessage.setGroupid(groupid);
        askQQMessage.setMsg("您是破坏者！");

        if (one == null){
            //插入第一条
            String sql = "update sky set one = " + "'" + helper_userid + "'" + "where userid = " + userid;
            JdbcUsers.update(sql,groupid);
        }
        else if (one != null && two == null){
            //插入第二条
            String sql = "update sky set two = " + "'" + helper_userid + "'" + "where userid = " + userid;
            JdbcUsers.update(sql,groupid);
        }
        else if (one != null && two != null && three == null){
            //插入第三条
            String sql = "update sky set three = " + "'" + helper_userid + "'" + "where userid = " + userid;
            JdbcUsers.update(sql,groupid);
            int help_num = (int)(Math.random()*100);
            if (help_num <= 25){
                askQQMessage.setQQID(one);
                ask = new Gson().toJson(askQQMessage);
                SkyService.updateWho(userid,groupid,one);
            }else if (help_num > 25 && help_num <= 50){
                askQQMessage.setQQID(two);
                ask = new Gson().toJson(askQQMessage);
                SkyService.updateWho(userid,groupid,two);
            }else if (help_num > 50 && help_num <=75){
                askQQMessage.setQQID(helper_userid);
                ask = new Gson().toJson(askQQMessage);
                SkyService.updateWho(userid,groupid,helper_userid);
            }else if (help_num > 75){
                askQQMessage.setQQID("1218129325");
                ask = new Gson().toJson(askQQMessage);
                SkyService.updateWho(userid,groupid,"nobody");
            }
        }
        else if (one != null && two != null && three != null){
            //人数已满
            System.out.println("人数已满！");
        }
    }
}
