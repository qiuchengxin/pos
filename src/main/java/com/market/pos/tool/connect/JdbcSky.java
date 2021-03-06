package com.market.pos.tool.connect;

import com.market.pos.config.DBClose;
import com.market.pos.tool.common.FinalData;

import java.sql.*;

public class JdbcSky {
    static String jdbcurl = FinalData.JDBCURL;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = FinalData.SQLUSRERNAME;
    static String password = FinalData.SQLPASSWORD;

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
     * @param sql
     * @param groupid
     */
    public static void searchSky(String sql,String groupid){
        String url = jdbcurl + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        result_userid = null;
        one = null;
        two = null;
        three = null;
        need = 0;
        done = 0;
        who = null;
        fail = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()){
                result_userid = result.getString("userid");
                need = result.getInt("need");
                done = result.getInt("done");
                who = result.getString("who");
                one = result.getString("one");
                two = result.getString("two");
                three = result.getString("three");
                fail = result.getInt("fail");
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.colseSqlConnection(result,statement,connection);
        }
    }
}
