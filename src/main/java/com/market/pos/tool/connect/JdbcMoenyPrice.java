package com.market.pos.tool.connect;

import java.sql.*;

public class JdbcMoenyPrice {
    static Connection connection;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String password = "123456";

    public static String money;
    public static String time;

    public static void searchCw(String sql,String groupid){
        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        money = null;
        time = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            if(!connection.isClosed()){
                System.out.println("----------------sky表查询---------------");
            }
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
               money = result.getString("money");
               time = result.getString("time");
            }
            connection.close();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
