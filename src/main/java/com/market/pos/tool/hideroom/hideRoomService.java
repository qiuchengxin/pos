package com.market.pos.tool.hideroom;import com.market.pos.tool.connect.JdbcBackpack;public class hideRoomService {    public static String userid;    public static String start_time;    public static int is_ok;    public static int do_hurt;    public static int is_hurt;    //插入userid    public static void insertHideRoom(String qqid){        String sql = "INSERT INTO hideroom ( userid ) VALUES ("                + "'"+ qqid+ "'" +                ")";        JdbcBackpack.insertBackPack(sql);    }    //更新    public static void updateHideRoom(String qqid,String data_type,String data){        String sql = "update hideroom set " + data_type + "=" + "'" +data+ "'" +"where userid = " + "'" +qqid+"'";        JdbcBackpack.updateBackPack(sql);    }    //查询    public static void selectHideRoom(String qqid){        String sql = "select * from hideroom where userid = " +"'"+ qqid +"'";        JdbcBackpack.searchHideRoom(sql);        userid= JdbcBackpack.userid;        start_time = JdbcBackpack.start_time;        is_ok = JdbcBackpack.is_ok;        do_hurt = JdbcBackpack.do_hurt;        is_hurt = JdbcBackpack.is_hurt;    }}