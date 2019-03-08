package com.market.pos.tool.TeamAdmin;

import com.market.pos.tool.connect.JdbcTeamAdmin;

public class TeamAdminService {
    public static int id;
    public static String t_id;
    public static String t_name;
    public static String t_type;
    public static String t_time;
    public static String liuyan;

    public static String result_userid;

    /**
     * 查询最新的团队信息team_list
     * @param groupid
     */
    public static void searchTeamList(String groupid,String t_from){
        String sql = "SELECT * from team_list where t_from = " + "'" + t_from + "'" + " ORDER BY id desc limit 1";
        JdbcTeamAdmin.searchTeamList(sql,groupid);
        t_id = JdbcTeamAdmin.t_id;
        t_name = JdbcTeamAdmin.t_name;
        t_type = JdbcTeamAdmin.t_type;
        t_time = JdbcTeamAdmin.t_time;
        liuyan = JdbcTeamAdmin.liuyan;
    }

    /**
     * 插入表team_members
     * @param groupid
     * @param userid
     * @param username
     * @param usertype
     */
    public static void insertTeamMembers(String groupid,String userid,String username,String usertype){
        String t_from = null;
        if (groupid.equals("721623673")){
            t_from = "皓水";
        }else if (groupid.equals("")){
            t_from = "风波渡";
        }
        searchTeamList("pay_data",t_from);
        String t_id = TeamAdminService.t_id;
        String sql = "INSERT INTO team_members (t_id,userid,username,usertype) VALUES (" +
                "'" + t_id + "'" + "," +
                "'" + userid + "'" + "," +
                "'" + username + "'" + "," +
                "'" + usertype + "'" + ")";
        System.out.println(sql);
        JdbcTeamAdmin.insertTeamList(sql,groupid);
    }

    /**
     * 查询表team_members
     * @param groupid
     * @param userid
     */
    public static void searchTeamMembers(String groupid,String userid){
        String t_from = null;
        if (groupid.equals("721623673")){
            t_from = "皓水";
        }else if (groupid.equals("")){
            t_from = "风波渡";
        }
        searchTeamList("pay_data",t_from);
        String t_id = TeamAdminService.t_id;
        String sql = "select * from team_members where t_id = " + "'" + t_id + "'" + " and userid = " + "'" +userid+ "'";
        JdbcTeamAdmin.searchTeamMembers(sql,groupid);
        result_userid = JdbcTeamAdmin.result_userid;
        System.out.println(sql);
    }

    public static void updateTeamMembers(String groupid,String userid,String t_id,String usertype,String username){
        String sql = "update team_members set usertype = " + "'" +usertype+ "'" + ",username = " + "'" + username +"'" +  " where t_id = " + "'" +t_id+ "'" + " and userid = " + "'" +userid+ "'";
        JdbcTeamAdmin.updateTeamList(sql,groupid);
        System.out.println(sql);
    }

    /**
     * 删除报名信息
     * @param groupid
     * @param userid
     * @param t_id
     */
    public static void delTeamMembers(String groupid,String userid,String t_id){
        String sql = "delete from team_members where userid = " + "'" + userid + "'" +
                " and t_id = " + "'" + t_id + "'" ;
        JdbcTeamAdmin.updateTeamList(sql,groupid);
        System.out.println(sql);
    }
}
