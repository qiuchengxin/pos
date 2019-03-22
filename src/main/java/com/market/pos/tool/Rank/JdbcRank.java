package com.market.pos.tool.Rank;

import com.market.pos.config.DBClose;
import com.market.pos.tool.common.FinalData;

import java.sql.*;

public class JdbcRank {

    static String jdbcurl = FinalData.JDBCURL;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = FinalData.SQLUSRERNAME;
    static String password = FinalData.SQLPASSWORD;

    public static String first_username;
    public static String second_username;
    public static String third_username;
    public static int first_grade;
    public static int second_grade;
    public static int third_grade;

    public static int myRankNum;

    public static void searchRank(String sql,String groupid){
        String url = jdbcurl + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
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
                String username = resultSet.getString("username");
                int grade = resultSet.getInt("grade");
                int rowNo = resultSet.getRow();
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
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.colseSqlConnection(resultSet,statement,connection);
        }
    }

    public static void searchRankByUserid(String sql,String groupid){
        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        myRankNum = 0;
        ResultSet result = null;
        Connection connection = null;
        Statement statement = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            if(!connection.isClosed()){
                System.out.println("----------------连接成功---------------");
            }
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()){
                myRankNum = result.getInt("rowNo");
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
