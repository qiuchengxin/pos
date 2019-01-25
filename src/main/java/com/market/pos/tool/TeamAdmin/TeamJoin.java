package com.market.pos.tool.TeamAdmin;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.connect.JdbcTeamAdmin;

public class TeamJoin {

//    public static String ask;

    public static String teamJoin(String t_id,String userid,String usertype,String groupid){

        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);
        askQQMessage.setQQID(userid);

        String msg = "[CQ:at,qq=" + userid + "] 自动排表失败！" +
                "\n原因可能是： " +
                "\n【1】您已经在排表里了" +
                "\n【2】或者您所报名的职业并不是团长所需要的哦！请检查后重新报名吧。 ";

        String[] tPositionArray = {
                            "t_11","t_12","t_13","t_14","t_15",
                            "t_21","t_22","t_23","t_24","t_25",
                            "t_31","t_32","t_33","t_34","t_35",
                            "t_41","t_42","t_43","t_44","t_45",
                            "t_51","t_52","t_53","t_54","t_55"};
        String[] arrayJob = new String[tPositionArray.length];

        /**
         *  通过useid查询最新团队信息中，该成员的属性值
         *  putin = 1:已报名 putin=0：未报名
         */
        String searchPutin = "select * from team_members where userid = " + "'" + userid + "'" + " and t_id = " + "'" + t_id + "'";
        JdbcTeamAdmin.searchTeamMembers(searchPutin,"pay_data");
        int putin = JdbcTeamAdmin.putin;
        System.out.println(putin);

        //查询team_importmember，获取该成员username
        String usernameFromImp = TeamJoin.searchImporMember(userid,groupid);

        //如果不是重要团员
        if (usernameFromImp == null){
            msg = "[CQ:at,qq=" + userid + "] 自动排表失败";
        }else {
            //遍历数据表team_tree
            for (int i = 0; i < tPositionArray.length; i++) {
                String tPosition = tPositionArray[i];
                String sql = "select " + tPosition + " from team_tree where t_id = " + "'" + t_id + "'";
                String tPositonJob = JdbcTeamAdmin.searchTeamTree(sql, "pay_data", tPosition);
                arrayJob[i] = tPositonJob;

                if (putin == 0 && usertype.equals(tPositonJob)) {
                    msg = "[CQ:at,qq=" + userid + "] 自动排表成功！" +
                            "\n温馨提示：对我说“看排表”可以查看排表哦！ ";

                    putin = 1;
                    arrayJob[i] = arrayJob[i] + " " + usernameFromImp;
                    String updateTeamTree = "update team_tree set " + tPosition + " = " + "'" + arrayJob[i] + "'" + "where t_id = " + "'" + t_id + "'";
                    JdbcTeamAdmin.updateTeamList(updateTeamTree, "pay_data");
                    String updatePutin = "update team_members set putin = '1' where userid = " + "'" + userid + "'" + " and t_id = " + "'" + t_id + "'";
                    JdbcTeamAdmin.updateTeamList(updatePutin, "pay_data");
                }
            }
        }
        askQQMessage.setMsg(msg);
        String ask = new Gson().toJson(askQQMessage);
        return ask;
    }

    //返回重要团员的自定义ID
    public static String searchImporMember(String userid,String groupid){
        String sql = "select * from team_impormember where userid = " + "'" + userid + "'" + "and groupid = " + "'" + groupid + "'";
        String username = JdbcTeamAdmin.searchTeamImpotMember(sql,"pay_data");
        return username;
    }
}
