package com.market.pos.tool.market;

import com.market.pos.config.DBClose;
import com.market.pos.tool.common.FinalData;

import java.sql.*;

public class JdbcMarket {
    static Connection connection;
    static String jdbcurl = FinalData.JDBCURL;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = FinalData.SQLUSRERNAME;
    static String password = FinalData.SQLPASSWORD;

    public static String userid;
    public static String buy_userid;
    public static String money;
    public static String good;

    public static void selectMarket(String sql,String groupid){
        String url = jdbcurl + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        userid = null;
        buy_userid = null;
        money = null;
        good = null;

        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            if(!connection.isClosed()){
                System.out.println("----------------连接成功---------------");
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                userid = resultSet.getString("userid");
                buy_userid = resultSet.getString("buy_userid");
                money = resultSet.getString("money");
                good = resultSet.getString("good");
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.colseSqlConnection(resultSet,statement,connection);
        }
    }
}
