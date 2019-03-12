package com.market.pos.tool.connect;

import com.market.pos.config.DBClose;
import com.market.pos.tool.common.FinalData;

import java.sql.*;

public class JdbcDaily {
    static String jdbcurl = FinalData.JDBCURL;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = FinalData.SQLUSRERNAME;
    static String password = FinalData.SQLPASSWORD;

    public static int i;
    public static int j;
    public static int k;

    /**
     * 查询日常随机数表
     * @param sql
     * @param groupid
     */
    public static void searchDaily(String sql,String groupid){
        String url = jdbcurl + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        i = 0;
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()){
               i = result.getInt("i");
               j = result.getInt("j");
               k = result.getInt("k");
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
