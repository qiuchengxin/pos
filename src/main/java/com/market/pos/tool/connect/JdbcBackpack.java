package com.market.pos.tool.connect;import java.sql.*;public class JdbcBackpack {    static Connection connection;    static String driver = "com.mysql.cj.jdbc.Driver";    static String user = "root";    static String password = "123456";    public static String equipment;    public static String moneny;    public static String book;    public static String special;    public static String resultUserId;    public static int finddaily;    public static String start_time;    public static int is_ok;    public static int do_hurt;    public static int is_hurt;    public static String userid;    public static int nowInHide;    //新增数据    public static void insertBackPack(String sql,String groupid){        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";        int result = 0;        try{            Class.forName(driver);            connection = DriverManager.getConnection(url,user,password);            if(!connection.isClosed()){                System.out.println("----------------新增backpack 连接成功---------------");            }            Statement statement = connection.createStatement();            result = statement.executeUpdate(sql);            connection.close();        }catch (ClassNotFoundException e) {            e.printStackTrace();        } catch (SQLException e) {            e.printStackTrace();        }    }    //更新数据    public static void updateBackPack(String sql,String groupid){        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";        try{            Class.forName(driver);            connection = DriverManager.getConnection(url,user,password);            if(!connection.isClosed()){                System.out.println("----------------update_backpack 连接成功---------------");            }            Statement statement = connection.createStatement();            statement.executeUpdate(sql);            connection.close();        }catch (ClassNotFoundException e) {            e.printStackTrace();        } catch (SQLException e) {            e.printStackTrace();        }    }    //查询数据    public static void searchBackPack(String sql,String groupid){        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";        finddaily = 0;        equipment = null;        moneny = null;        book = null;        special = null;        resultUserId = null;        try{            Class.forName(driver);            connection = DriverManager.getConnection(url,user,password);            Statement statement = connection.createStatement();            ResultSet result = statement.executeQuery(sql);            while (result.next()){                resultUserId = result.getString("userid");                equipment = result.getString("equipment");                moneny = result.getString("money");                book = result.getString("book");                special = result.getString("special");                finddaily = result.getInt("finddaily");            }            System.out.println(result);            connection.close();        }catch (ClassNotFoundException e) {            e.printStackTrace();        } catch (SQLException e) {            e.printStackTrace();        }    }    //查询数据,闭关数据表hideroom    public static void searchHideRoom(String sql,String groupid){        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";        userid = null;        start_time = null;        is_ok = 0;        do_hurt = 0;        is_hurt = 0;        try{            Class.forName(driver);            connection = DriverManager.getConnection(url,user,password);            Statement statement = connection.createStatement();            ResultSet result = statement.executeQuery(sql);            while (result.next()){                userid = result.getString("userid");                start_time = result.getString("start_time");                is_ok = result.getInt("is_ok");                is_hurt = result.getInt("is_hurt");                do_hurt = result.getInt("do_hurt");            }            System.out.println(result);            connection.close();        }catch (ClassNotFoundException e) {            e.printStackTrace();        } catch (SQLException e) {            e.printStackTrace();        }    }    //查询闭关人数    public static void findHide(String sql,String groupid){        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";        nowInHide = 0;        try{            Class.forName(driver);            connection = DriverManager.getConnection(url,user,password);            Statement statement = connection.createStatement();            ResultSet result = statement.executeQuery(sql);            while (result.next()){                nowInHide = result.getInt("count(is_ok)");            }            System.out.println(result);            connection.close();        }catch (ClassNotFoundException e) {            e.printStackTrace();        } catch (SQLException e) {            e.printStackTrace();        }    }}