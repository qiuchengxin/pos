package com.market.pos.tool.TeamAdmin;

import com.market.pos.tool.connect.JdbcDaily;
import com.market.pos.tool.connect.JdbcTeamAdmin;

public class TeamOut {
    public static void teamOut(String userid,String groupid,String t_id){
        String[] tPositionArray = {
                "t_11","t_12","t_13","t_14","t_15",
                "t_21","t_22","t_23","t_24","t_25",
                "t_31","t_32","t_33","t_34","t_35",
                "t_41","t_42","t_43","t_44","t_45",
                "t_51","t_52","t_53","t_54","t_55"};
//        String[] arrayJob = new String[tPositionArray.length];

        String sql = "select username from team_impormember where userid = " + "'" + userid + "'" +
                " and groupid = " + "'" + groupid + "'";
        String username = JdbcTeamAdmin.searchTeamImpotMember(sql,"pay_data");
        for (int i=0;i<tPositionArray.length;i++){
            String tPosition = tPositionArray[i];

            String teamTreeSql = "select " + tPosition + " from team_tree where t_id = " + "'" + t_id + "'";
            String tPositonJob = JdbcTeamAdmin.searchTeamTree(teamTreeSql, "pay_data", tPosition);

            if (tPositonJob.matches(".*" + username + ".*")){
                String updateJob = tPositonJob.replaceAll(username,"").replaceAll(" ","");
                String updateSql = "update team_tree set " + tPosition + " = " + "'" + updateJob + "'" +
                        " where t_id = " + "'" + t_id + "'" ;
                JdbcTeamAdmin.updateTeamList(updateSql,"pay_data");
            }
        }
    }
}
