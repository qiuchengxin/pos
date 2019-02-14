package com.market.pos.tool.connect;

import java.sql.*;

public class JdbcHong {
    static Connection connection;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String password = "123456";

    public static String content;

    /**
     * 查询hong
     * @param sql
     * @param groupid
     */
    public static void searchHong(String sql,String groupid){
        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        content = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                content = result.getString("content");
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
