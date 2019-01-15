package com.market.pos.tool.connect;

import java.sql.*;

public class JdbcSky {
    static Connection connection;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String password = "123456";

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
        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        result_userid = null;
        one = null;
        two = null;
        three = null;
        need = 0;
        done = 0;
        who = null;
        fail = 0;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
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
            connection.close();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
