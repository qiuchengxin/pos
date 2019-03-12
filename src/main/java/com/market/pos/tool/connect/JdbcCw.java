package com.market.pos.tool.connect;

import com.market.pos.config.DBClose;
import com.market.pos.tool.common.FinalData;

import java.sql.*;

public class JdbcCw {
    static String jdbcurl = FinalData.JDBCURL;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = FinalData.SQLUSRERNAME;
    static String password = FinalData.SQLPASSWORD;

    public static String result_userid;
    public static int is_doudi;
    public static String hurt;
    public static int daily;
    public static String cw_name;
    public static int cd;

    /**
     * 查询cw表
     * @param sql
     * @param groupid
     */
    public static void searchCw(String sql,String groupid){
        String url = jdbcurl + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        result_userid = null;
        is_doudi = 0;
        hurt = null;
        daily = 0;
        cd = 0;
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
                is_doudi = result.getInt("is_doudi");
                hurt = result.getString("hurt");
                daily = result.getInt("daily");
                cw_name = result.getString("cw_name");
                cd = result.getInt("cd");
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
