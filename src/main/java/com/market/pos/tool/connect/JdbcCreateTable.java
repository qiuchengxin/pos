package com.market.pos.tool.connect;

import com.market.pos.config.DBClose;
import com.market.pos.tool.common.FinalData;

import java.sql.*;

public class JdbcCreateTable {

    static String jdbcurl = FinalData.JDBCURL;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String user = FinalData.SQLUSRERNAME;
    static String password = FinalData.SQLPASSWORD;


    /**
     * 查询team_tree表
     * @param sql
     * @param groupid
     */
    public static String[][] searchQA(String sql,String groupid){
        String url = jdbcurl + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
//        String answer = null;
        String[][] members = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()){
                //第一行
                String t_11 = result.getString("t_11");
                String t_12 = result.getString("t_12");
                String t_13 = result.getString("t_13");
                String t_14 = result.getString("t_14");
                String t_15 = result.getString("t_15");
                //第二行
                String t_21 = result.getString("t_21");
                String t_22 = result.getString("t_22");
                String t_23 = result.getString("t_23");
                String t_24 = result.getString("t_24");
                String t_25 = result.getString("t_25");
                //第三行
                String t_31 = result.getString("t_31");
                String t_32 = result.getString("t_32");
                String t_33 = result.getString("t_33");
                String t_34 = result.getString("t_34");
                String t_35 = result.getString("t_35");
                //第四行
                String t_41 = result.getString("t_41");
                String t_42 = result.getString("t_42");
                String t_43 = result.getString("t_43");
                String t_44 = result.getString("t_44");
                String t_45 = result.getString("t_45");
                //第五行
                String t_51 = result.getString("t_51");
                String t_52 = result.getString("t_52");
                String t_53 = result.getString("t_53");
                String t_54 = result.getString("t_54");
                String t_55 = result.getString("t_55");
                //组装
                members = new String[][]{
                        {t_11, t_12, t_13, t_14, t_15},
                        {t_21, t_22, t_23, t_24, t_25},
                        {t_31, t_32, t_33, t_34, t_35},
                        {t_41, t_42, t_43, t_44, t_45},
                        {t_51, t_52, t_53, t_54, t_55}
                };
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.colseSqlConnection(result,statement,connection);
        }
        return members;
    }
}
