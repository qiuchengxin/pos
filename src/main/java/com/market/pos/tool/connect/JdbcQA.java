package com.market.pos.tool.connect;

import com.market.pos.config.DBClose;

import java.sql.*;

public class JdbcQA {

    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String password = "123456";


    /**
     * 查询qa表
     * @param sql
     * @param groupid
     */
    public static String searchQA(String sql,String groupid){
        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        String answer = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()){
                answer = result.getString("answer");
            }
//            return question;
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.colseSqlConnection(result,statement,connection);
        }
        return answer;
    }
}
