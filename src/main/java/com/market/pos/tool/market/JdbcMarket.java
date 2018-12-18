package com.market.pos.tool.market;

import java.sql.*;

public class JdbcMarket {
    static Connection connection;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String password = "123456";

    public static String userid;
    public static String buy_userid;
    public static String money;
    public static String good;

    public static void selectMarket(String sql,String groupid){
        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        userid = null;
        buy_userid = null;
        money = null;
        good = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            if(!connection.isClosed()){
                System.out.println("----------------连接成功---------------");
            }
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                userid = result.getString("userid");
                buy_userid = result.getString("buy_userid");
                money = result.getString("money");
                good = result.getString("good");
            }
            connection.close();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
