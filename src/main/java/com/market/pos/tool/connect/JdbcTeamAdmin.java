package com.market.pos.tool.connect;

import java.sql.*;

public class JdbcTeamAdmin {
    static Connection connection;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String password = "123456";

    public static int id;
    public static String t_id;
    public static String t_name;
    public static String t_type;
    public static String t_time;
    public static String liuyan;

    public static String result_userid;

    /**
     * 查询team_list表
     * @param sql
     * @param groupid
     */
    public static void searchTeamList(String sql,String groupid){
        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        t_name = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            if(!connection.isClosed()){
                System.out.println("----------------sky表查询---------------");
            }
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                id = result.getInt("id");
                t_id = result.getString("t_id");
                t_name = result.getString("t_name");
                t_type = result.getString("t_type");
                t_time = result.getString("t_time");
                liuyan = result.getString("liuyan");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入表team_list
     * @param sql
     * @param groupid
     */
    public static void insertTeamList(String sql,String groupid){
        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        int result = 0;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            if(!connection.isClosed()){
                System.out.println("----------------连接成功---------------");
            }
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(sql);
            if(result != 0 ){
                System.out.println("插入成功！");
            }else {
                System.out.println("插入失败！");
            }
            connection.close();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新表team_list
     * @param sql
     * @param groupid
     */
    public static void updateTeamList(String sql,String groupid){
        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            if(!connection.isClosed()){
                System.out.println("----------------连接成功---------------");
            }
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询表Team_Members
     * @param sql
     * @param groupid
     */
    public static void searchTeamMembers(String sql,String groupid){
        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        result_userid = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                result_userid = result.getString("userid");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
