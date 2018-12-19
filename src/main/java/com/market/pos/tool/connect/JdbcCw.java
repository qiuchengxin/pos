package com.market.pos.tool.connect;

import java.sql.*;

public class JdbcCw {

    static Connection connection;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String password = "123456";

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
        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        result_userid = null;
        is_doudi = 0;
        hurt = null;
        daily = 0;
        cd = 0;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            if(!connection.isClosed()){
                System.out.println("----------------sky表查询---------------");
            }
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                result_userid = result.getString("userid");
                is_doudi = result.getInt("is_doudi");
                hurt = result.getString("hurt");
                daily = result.getInt("daily");
                cw_name = result.getString("cw_name");
                cd = result.getInt("cd");
            }
            connection.close();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
