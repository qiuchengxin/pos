package com.market.pos.tool.TeamAdmin;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.connect.JdbcTeamAdmin;
import com.market.pos.tool.pk.GetQid;

public class TeamAdminController {

    //查询团队信息
    public static String searchTeamList(String groupid,String qqid){
        String t_from = null;
        if (groupid.equals("721623673")){
            t_from = "皓水";
        }else if (groupid.equals("921340922")){
            t_from = "风波渡";
        }
        TeamAdminService.searchTeamList("pay_data",t_from);
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
                    "\n团队：" + t_from +
                    "\n日期：" + t_time +
                    "\n副本：" + t_type + " " + t_name +
                    "\n若要报名请对我说：【我要报名xxx】,xxx为心法名称");
            ask = new Gson().toJson(askQQMessage);
        }else if (t_name == null){
            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 当前并没有招募信息！");
            ask = new Gson().toJson(askQQMessage);
        }
        return ask;
    }

    /**
     * 点招募
     */

    public static String insertTeamMembers(String groupid,String userid,String username,String msg,String nick){
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);
        askQQMessage.setQQID(userid);
        String t_from = null;
        if (groupid.equals("721623673")){
            t_from = "皓水";
        }else if (groupid.equals("921340922")){
            t_from = "风波渡";
        }
        TeamAdminService.searchTeamList("pay_data",t_from);
        String t_id = TeamAdminService.t_id;
        GetQid.getUserType(msg);
        String usertype = GetQid.ch_msg;
        String ask = null;
        //如果群名片不为空，则username为群名片，若为空，则username为qq昵称
        if (nick != null || !nick.equals("")){
            username = nick;
        }

        if (t_id != null){
            TeamAdminService.searchTeamMembers("pay_data",userid);
            String result_userid = TeamAdminService.result_userid;
            if (result_userid == null){
                askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 报名成功！你报名的职业是： " + usertype
                                                            + "\n副本团" + t_from
                                                            + "\n查看排表请对我说【看排表】"
                                                            + "\n若要取消，请对我说【取消报名】");
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
        String t_from = null;
        if (groupid.equals("721623673")){
            t_from = "皓水";
        }else if (groupid.equals("921340922")){
            t_from = "风波渡";
        }
        TeamAdminService.searchTeamList("pay_data",t_from);
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
            askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 取消报名成功！下次记得积极报名吧！");
            ask = new Gson().toJson(askQQMessage);

            int putin = JdbcTeamAdmin.putin;

            if (putin == 1) {
                TeamOut.teamOut(userid, groupid, t_id);
                String updatePutin = "update team_members set putin = '0' where userid = " + "'" + userid + "'" +
                        " and t_id = " + "'" + t_id + "'";
                JdbcTeamAdmin.updateTeamList(updatePutin,"pay_data");
            }
            //删除报名信息
            TeamAdminService.delTeamMembers("pay_data", userid, t_id);
            String sql = "select putin from team_members where userid = " + "'" + userid + "'"
                    + " and t_id = " + "'" + t_id + "'";
            JdbcTeamAdmin.searchTeamMembers(sql,"pay_data");
        }
        return ask;
    }
}
