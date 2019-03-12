package com.market.pos.tool.connect;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.market.pos.config.DBClose;
import com.market.pos.tool.common.FinalData;

import java.sql.*;

public class JdbcTest {
    static String jdbcurl = FinalData.JDBCURL;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = FinalData.SQLUSRERNAME;
    static String password = FinalData.SQLPASSWORD;

    /**
     * 查询问题表question
     * @param sql
     * @param groupid
     * @return
     */
    public static String searchQuestion(String sql, String groupid){
        String url = jdbcurl + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        JSONObject jsonObject = new JSONObject();
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()){
                int id = result.getInt("id");
                String content = result.getString("content");
                int answerId = result.getInt("answer_id");
                //填充json
                jsonObject.put("id",id);
                jsonObject.put("content",content);
                jsonObject.put("answerId",answerId);
            }
//            return question;
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.colseSqlConnection(result,statement,connection);
        }
        return jsonObject.toJSONString();
    }

    /**
     * 查询随机数
     * @param sql
     * @param groupid
     * @return
     */
    public static String searchRank(String sql, String groupid){
        String url = jdbcurl + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        JSONArray jsonArray = new JSONArray();
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()){
                int id = result.getInt("id");
                int option = result.getInt("option");
                //填充json
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",id);
                jsonObject.put("option",option);
                jsonArray.add(jsonObject);
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.colseSqlConnection(result,statement,connection);
        }
        return jsonArray.toJSONString();
    }

    /**
     * 查询正确答案
     * @param sql
     * @param groupid
     * @return
     */
    public static String searchAnswer(String sql, String groupid){
        String url = jdbcurl + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        JSONObject jsonObject = new JSONObject();
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()){
                int id = result.getInt("id");
                String content = result.getString("content");
                int questionId = result.getInt("question_id");
                //填充json
                jsonObject.put("id",id);
                jsonObject.put("content",content);
                jsonObject.put("questionId",questionId);
            }
//            return question;
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.colseSqlConnection(result,statement,connection);
        }
        return jsonObject.toJSONString();
    }

    /**
     * 查询三个错误答案
     * @param sql
     * @param groupid
     * @return
     */
    public static String searchWrongAnswers(String sql, String groupid){
        String url = jdbcurl + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        JSONArray jsonArray = new JSONArray();
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()){
                int id = result.getInt("id");
                String content = result.getString("content");
                int questionId = result.getInt("question_id");
                //填充json
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",id);
                jsonObject.put("content",content);
                jsonObject.put("questionId",questionId);
                jsonArray.add(jsonObject);
            }
//            return question;
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.colseSqlConnection(result,statement,connection);
        }
        return jsonArray.toJSONString();
    }

    /**
     * 插入题目
     * @param sql
     * @param groupid
     */
    public static void insertTest(String sql,String groupid){
        String url = jdbcurl + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.colseSqlConnection(result,statement,connection);
        }
    }

    //查询表test_list
    public static String searchTest(String sql, String groupid){
        String url = jdbcurl + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        JSONObject jsonObject = new JSONObject();
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()){
                String questionContent = result.getString("question_content");
                String answer = result.getString("answer");
                String a = result.getString("a");
                String b = result.getString("b");
                String c = result.getString("c");
                String d = result.getString("d");
                int isRight = result.getInt("is_right");
                String time = result.getString("time");
                //填充json
                jsonObject.put("questionContent",questionContent);
                jsonObject.put("answer",answer);
                jsonObject.put("a",a);
                jsonObject.put("b",b);
                jsonObject.put("c",c);
                jsonObject.put("d",d);
                jsonObject.put("isRight",isRight);
                jsonObject.put("time",time);
            }
//            return question;
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.colseSqlConnection(result,statement,connection);
        }
        return jsonObject.toJSONString();
    }
}
