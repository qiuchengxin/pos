package com.market.pos.tool.dujie;

import com.market.pos.tool.connect.JdbcSky;
import com.market.pos.tool.connect.JdbcUsers;

public class SkyService {
    public static String result_userid;
    public static String one;
    public static String two;
    public static String three;
    public static int need;
    public static int done;
    public static String who;
    public static int fail;

    /**
     * 查询sky表
     * @param userid
     * @param groupid
     */
    public static void searchSky(String userid,String groupid){
        String sql = "select * from sky where userid = " + userid;
        JdbcSky.searchSky(sql,groupid);
        result_userid = JdbcSky.result_userid;
        need = JdbcSky.need;
        done = JdbcSky.done;
        one = JdbcSky.one;
        two = JdbcSky.two;
        three = JdbcSky.three;
        who = JdbcSky.who;
        fail = JdbcSky.fail;
    }

    /**
     * 插入sky表userid
     * @param userid
     * @param groupid
     */
    public static void insertSky(String userid,String groupid){
        String sql = "INSERT into sky (userid) VALUES (" + "'" + userid + "'" +")";
        JdbcUsers.insert(sql,groupid);
    }

    /**
     * 更新need字段为1
     * @param userid
     * @param groupid
     */
    public static void updateSkyNeed(String userid,String groupid){
        String sql = "update sky set need = 1 where userid = " + userid;
        JdbcUsers.update(sql,groupid);
    }

    /**
     * 更新done字段为1
     * @param userid
     * @param groupid
     */
    public static void updateSkyDone(String userid,String groupid){
        String sql = "update sky set done = 1 where userid = " + userid;
        JdbcUsers.update(sql,groupid);
    }

    /**
     * 删除指定userid的用户
     * @param userid
     * @param groupid
     */
    public static void delSky(String userid,String groupid){
        String sql = "delete from sky where userid = " + userid;
        JdbcUsers.update(sql,groupid);
    }

    /**
     * 更新who字段为heler_userid
     * @param userid
     * @param groupid
     * @param helper_userid
     */
    public static void updateWho(String userid,String groupid,String helper_userid){
        String sql = "update sky set who = " + "'" + helper_userid + "'" + "where userid = " + userid;
        JdbcUsers.update(sql,groupid);
    }

    /**
     * 将渡劫次数更新为1
     * @param userid
     * @param groupid
     */
    public static void updateFail(String userid,String groupid){
        String sql = "update sky set fail = 1 where userid = " + userid;
        JdbcUsers.update(sql,groupid);
    }
}
