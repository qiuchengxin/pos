package com.market.pos.tool.connect;import java.sql.*;public class JdbcFind {    static Connection connection;    static String driver = "com.mysql.cj.jdbc.Driver";    static String url = "jdbc:mysql://148.70.49.2:3306/pay_data?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";    static String user = "root";    static String password = "123456";    public static String equipment;    public static String moneny;    public static String book;    public static String special;    public static int finddaily;    //新增数据    public static void insertBackPack(String sql){        int result = 0;        try{            Class.forName(driver);            connection = DriverManager.getConnection(url,user,password);            if(!connection.isClosed()){                System.out.println("----------------新增backpack 连接成功---------------");            }            Statement statement = connection.createStatement();            result = statement.executeUpdate(sql);            connection.close();        }catch (ClassNotFoundException e) {            e.printStackTrace();        } catch (SQLException e) {            e.printStackTrace();        }    }    //更新数据    public static void updateBackPack(String sql){        try{            Class.forName(driver);            connection = DriverManager.getConnection(url,user,password);            if(!connection.isClosed()){                System.out.println("----------------update_backpack 连接成功---------------");            }            Statement statement = connection.createStatement();            statement.executeUpdate(sql);            connection.close();        }catch (ClassNotFoundException e) {            e.printStackTrace();        } catch (SQLException e) {            e.printStackTrace();        }    }    //查询数据    public static void searchBackPack(String sql){        finddaily = 0;        try{            Class.forName(driver);            connection = DriverManager.getConnection(url,user,password);            if(!connection.isClosed()){                System.out.println("----------------search 连接成功---------------");            }            Statement statement = connection.createStatement();            ResultSet result = statement.executeQuery(sql);            while (result.next()){                equipment = result.getString("equipment");                moneny = result.getString("money");                book = result.getString("book");                special = result.getString("special");                finddaily = result.getInt("finddaily");            }            System.out.println(result);            connection.close();        }catch (ClassNotFoundException e) {            e.printStackTrace();        } catch (SQLException e) {            e.printStackTrace();        }    }}