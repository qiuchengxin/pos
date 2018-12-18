package com.market.pos.tool.Rank;

import java.sql.*;

public class JdbcRank {

    static Connection connection;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String password = "123456";

    public static String first_username;
    public static String second_username;
    public static String third_username;
    public static int first_grade;
    public static int second_grade;
    public static int third_grade;

    public static int myRankNum;

    public static void searchRank(String sql,String groupid){
        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            if(!connection.isClosed()){
                System.out.println("----------------连接成功---------------");
            }
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                String username = result.getString("username");
                int grade = result.getInt("grade");
                int rowNo = result.getRow();
                if (rowNo == 1){
                    first_username = username;
                    first_grade = grade;
                }
                if (rowNo == 2){
                    second_username = username;
                    second_grade = grade;
                }
                if (rowNo == 3){
                    third_username = username;
                    third_grade = grade;
                }
            }
            connection.close();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void searchRankByUserid(String sql,String groupid){
        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        myRankNum = 0;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            if(!connection.isClosed()){
                System.out.println("----------------连接成功---------------");
            }
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                myRankNum = result.getInt("rowNo");
            }
            connection.close();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
