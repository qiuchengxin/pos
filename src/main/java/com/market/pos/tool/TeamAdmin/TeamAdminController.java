package com.market.pos.tool.TeamAdmin;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.pk.GetQid;

public class TeamAdminController {

    //查询团队信息
    public static String searchTeamList(String groupid,String qqid){
        TeamAdminService.searchTeamList("pay_data");
        String t_name = TeamAdminService.t_name;
        String t_type = TeamAdminService.t_type;
        String t_time = TeamAdminService.t_time;

        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);
        askQQMessage.setQQID(qqid);
        String ask = null;
        if (t_name != null){
            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 最新的团队招募如下：" +
                    "\n日期：" + t_time +
                    "\n副本：" + t_type + " " + t_name);
            ask = new Gson().toJson(askQQMessage);
        }else if (t_name == null){
            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 并没有招募信息！");
            ask = new Gson().toJson(askQQMessage);
        }
        return ask;
    }

    //点招募
    public static String insertTeamMembers(String groupid,String userid,String username,String msg){
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);
        askQQMessage.setQQID(userid);
        TeamAdminService.searchTeamList("pay_data");
        String t_id = TeamAdminService.t_id;
        GetQid.getUserType(msg);
        String usertype = GetQid.ch_msg;
        String ask = null;
        if (t_id != null){
            TeamAdminService.searchTeamMembers("pay_data",userid);
            String result_userid = TeamAdminService.result_userid;
            if (result_userid == null){
                askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 报名成功！你报名的职业是： " + usertype + ", 若是进入排表，本机器人会私聊通知到您。");
                ask = new Gson().toJson(askQQMessage);
                TeamAdminService.insertTeamMembers("pay_data",userid,username,usertype);
            }else if (result_userid != null){
                askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 你已经报名过了！已将您之前报名的职业更新为： " + usertype);
                ask = new Gson().toJson(askQQMessage);
                TeamAdminService.updateTeamMembers("pay_data",userid,t_id,usertype,username);
            }
        }else if (t_id == null){
            askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 现在还没有招募哦！");
            ask = new Gson().toJson(askQQMessage);
        }
        return ask;
    }

    //取消报名
    public static String delTeamMembers(String userid,String groupid){
        String ask = null;
        TeamAdminService.searchTeamList("pay_data");
        String t_id = TeamAdminService.t_id;
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);
        askQQMessage.setQQID(userid);

        TeamAdminService.searchTeamMembers("pay_data",userid);
        String result_userid = TeamAdminService.result_userid;
        if (result_userid == null){
            askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 你并没有报名哦！");
            ask = new Gson().toJson(askQQMessage);
        }else if (result_userid != null) {
            askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 取消报名成功！");
            ask = new Gson().toJson(askQQMessage);
            //删除报名信息
            TeamAdminService.delTeamMembers("pay_data", userid, t_id);
        }
        return ask;
    }
}
