package com.market.pos.tool.common;

/**
 * 定义常量，迁移数据或服务器时需要修改此类
 */
public class FinalData {

    //数据库账号
    public static final String SQLUSRERNAME = "root";
    //数据库密码
    public static final String SQLPASSWORD = "haoshuiroot";
    //数据库端口
    public static final String SQLPORT = "3306";
    //服务器IP
    public static final String SERVERURL = "182.254.189.186";
    //JDBC数据库URL
    public static final String JDBCURL = "jdbc:mysql://" + SERVERURL + ":" + SQLPORT + "/";

}
