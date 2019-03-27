package com.market.pos.tool.connect;

import com.market.pos.config.DBClose;
import com.market.pos.tool.common.FinalData;

import java.sql.*;

public class JdbcServerOpenSearch {

    static String jdbcurl = FinalData.JDBCURL;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = FinalData.SQLUSRERNAME;
    static String password = FinalData.SQLPASSWORD;


    /**
     * 查询servers_ip表
     * @param sql
     * @param groupid
     */
    public static String searchHostIP(String sql,String groupid){
        String url = jdbcurl + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        String host_ip = "UNKNOW";
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()){
                host_ip = result.getString("host_ip");
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.colseSqlConnection(result,statement,connection);
        }
        return host_ip;
    }

    public static String searchQyId(String sql,String groupid){
        String url = jdbcurl + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        String qyId = "0";
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()){
                qyId = result.getString("qy_id");
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.colseSqlConnection(result,statement,connection);
        }
        return qyId;
    }
}
