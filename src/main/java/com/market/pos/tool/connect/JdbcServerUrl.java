package com.market.pos.tool.connect;

import com.market.pos.config.DBClose;
import com.market.pos.tool.common.FinalData;

import java.sql.*;

public class JdbcServerUrl {
    static String jdbcurl = FinalData.JDBCURL;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = FinalData.SQLUSRERNAME;
    static String password = FinalData.SQLPASSWORD;

    public static String serverName;
    public static String serverUrl;

    public static void searchServerUrl(String sql,String groupid){
        String url = jdbcurl + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        serverName = null;
        serverUrl = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()){
                serverName = result.getString("server_name");
                serverUrl = result.getString("server_url");
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
