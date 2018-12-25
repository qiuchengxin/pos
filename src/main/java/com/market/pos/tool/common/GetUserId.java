package com.market.pos.tool.common;

import com.market.pos.tool.connect.JdbcUserID;

public class GetUserId {

    public static String result_userid;

    public static void getUserId(String userid,String groupid){
        String sql = "select userid from users where userid = " + userid;
        result_userid = JdbcUserID.searchUserID(sql,groupid);
    }
}
