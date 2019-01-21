package com.market.pos.tool.connect;

import java.sql.*;

public class JdbcDaily {
    static Connection connection;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String password = "123456";

    public static int i;
    public static int j;
    public static int k;

    /**
     * 查询日常随机数表
     * @param sql
     * @param groupid
     */
    public static void searchDaily(String sql,String groupid){
        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        i = 0;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
               i = result.getInt("i");
               j = result.getInt("j");
               k = result.getInt("k");
            }
            connection.close();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
