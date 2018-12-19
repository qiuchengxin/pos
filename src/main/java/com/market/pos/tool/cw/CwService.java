package com.market.pos.tool.cw;

import com.market.pos.tool.connect.JdbcCw;
import com.market.pos.tool.connect.JdbcUsers;

public class CwService {
    public static String result_userid;
    public static int is_doudi;
    public static String hurt;
    public static int daily;
    public static String cw_name;
    public static int cd;

    /**
     * 查询cw表
     * @param userid
     * @param groupid
     */
    public static void searchCw(String userid,String groupid){
        String sql = "select * from cw where userid = " + userid;
        JdbcCw.searchCw(sql,groupid);
        result_userid = JdbcCw.result_userid;
        is_doudi = JdbcCw.is_doudi;
        hurt = JdbcCw.hurt;
        daily = JdbcCw.daily;
        cw_name = JdbcCw.cw_name;
        cd = JdbcCw.cd;
    }

    /**
     *往cw表中插入userid
     * @param userid
     * @param groupid
     */
    public static void insertCw(String userid,String groupid){
        String sql = "INSERT into cw (userid) VALUES (" + "'" + userid + "'" +")";
        JdbcUsers.insert(sql,groupid);
    }

    /**
     * 将is_doudi置为1 , 更新hurt,daiy = 1, cw_name 为自定义的名称
     * @param userid
     * @param groupid
     */
    public static void updateIsDoudi(String userid,String groupid){
        String sql = "update cw set is_doudi = 1 where userid = " + userid;
        JdbcUsers.update(sql,groupid);
    }

    public static void updateHurt(String userid,String groupid,String hurt){
        String sql = "update cw set hurt = " + hurt + " where userid = " + userid;
        JdbcUsers.update(sql,groupid);
    }

    public static void updateDaily(String userid,String groupid){
        String sql = "update cw set daily = 1 where userid = " + userid;
        JdbcUsers.update(sql,groupid);
    }

    public static void updateCwName(String userid,String groupid,String cw_name){
        String sql = "update cw set cw_name = " + "'" + cw_name + "'" + " where userid = " + userid;
        JdbcUsers.update(sql,groupid);
        System.out.println(sql);
    }

    /**
     * 删除指定userid的用户
     * @param userid
     * @param groupid
     */
    public static void delCw(String userid,String groupid){
        String sql = "delete from cw where userid = " + userid;
        JdbcUsers.update(sql,groupid);
    }

    /**
     * 删除backpack中的玄晶
     * @param userid
     * @param groupid
     */
    public static void delBackPackSpecial(String userid,String groupid){
        String sql = "update backpack set special = null where userid = " + userid;
        JdbcUsers.update(sql,groupid);
    }

    /**
     * 更新equip中的CW
     * @param userid
     * @param groupid
     * @param cw_name
     */
    public static void updateEquipSpecial(String userid,String groupid,String cw_name){
        String sql = "update equip set special = " + "'" + cw_name + "'" + " where userid = " + userid;
        JdbcUsers.update(sql,groupid);
    }

    /**
     * 更新cd为1
     * @param userid
     * @param groupid
     */
    public static void updateCd(String userid,String groupid){
        String sql = "update cw set cd = 1 where userid = " + userid;
        JdbcUsers.update(sql,groupid);
    }
}
