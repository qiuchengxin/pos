package com.market.pos.tool.lemoc;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class test {
    static Connection connection;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = "root";
    static String password = "123456";

    public static String ask;

    public static void insert(String sql,String groupid){
        String url = "jdbc:mysql://localhost:3306/pay_data?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
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

    public static void insertTest(String qqid,String nick,String groupid){
        String sql = "insert into test (userid,username,groupid) values ("
                    + "'" + qqid + "'" +
                "," + "'" + nick + "'" +
                "," + "'" + groupid+ "'" + ")";
        test.insert(sql,groupid);
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);
        askQQMessage.setQQID(qqid);
        askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 数据记录成功，操作一次即可 ！ " +
                "\n今晚之前数据完成记录并与晚上十点前迁移数据库 ！ ");
        ask = new Gson().toJson(askQQMessage);
    }
}