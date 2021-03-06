package com.market.pos.tool.connect;

import com.market.pos.config.DBClose;
import com.market.pos.tool.common.FinalData;

import java.sql.*;

public class JdbcUserID {
    static String jdbcurl = FinalData.JDBCURL;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = FinalData.SQLUSRERNAME;
    static String password = FinalData.SQLPASSWORD;


    public static String searchUserID(String sql,String groupid){
        String url = jdbcurl + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        String result_userid = null;
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
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.colseSqlConnection(result,statement,connection);
        }
        return result_userid;
    }
}
