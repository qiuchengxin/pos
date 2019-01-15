package com.market.pos.tool.connect;import java.sql.*;public class JdbcUsers {    static Connection connection;    static String driver = "com.mysql.cj.jdbc.Driver";    static String user = "root";    static String password = "123456";    public static int grade;    public static int daily;    public static int pkdaily;    public static void searchGrade(String sql,String groupid){        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";        grade = 0;        try{            Class.forName(driver);            connection = DriverManager.getConnection(url,user,password);            Statement statement = connection.createStatement();            ResultSet result = statement.executeQuery(sql);            while (result.next()){                grade = Integer.parseInt(result.getString("grade"));            }            connection.close();        }catch (ClassNotFoundException e) {            e.printStackTrace();        } catch (SQLException e) {            e.printStackTrace();        }    }    public static void searchDaily(String sql,String groupid){        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";        try{            Class.forName(driver);            connection = DriverManager.getConnection(url,user,password);            Statement statement = connection.createStatement();            ResultSet result = statement.executeQuery(sql);            while (result.next()){                daily = Integer.parseInt(result.getString("daily"));            }            connection.close();        }catch (ClassNotFoundException e) {            e.printStackTrace();        } catch (SQLException e) {            e.printStackTrace();        }    }    public static void insert(String sql,String groupid){        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";        int result = 0;        try{            Class.forName(driver);            connection = DriverManager.getConnection(url,user,password);            Statement statement = connection.createStatement();            result = statement.executeUpdate(sql);            if(result != 0 ){                System.out.println("插入成功！");            }else {                System.out.println("插入失败！");            }            connection.close();        }catch (ClassNotFoundException e) {            e.printStackTrace();        } catch (SQLException e) {            e.printStackTrace();        }    }    public static void update(String sql,String groupid){        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";        try{            Class.forName(driver);            connection = DriverManager.getConnection(url,user,password);            Statement statement = connection.createStatement();            statement.executeUpdate(sql);            connection.close();        }catch (ClassNotFoundException e) {            e.printStackTrace();        } catch (SQLException e) {            e.printStackTrace();        }    }    public static void searchPkdaily(String sql,String groupid){        String url = "jdbc:mysql://148.70.49.2:3306/" + groupid + "?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";        try{            Class.forName(driver);            connection = DriverManager.getConnection(url,user,password);            Statement statement = connection.createStatement();            ResultSet result = statement.executeQuery(sql);            while (result.next()){                pkdaily = Integer.parseInt(result.getString("pkdaily"));                System.out.println("pkdaily : "+pkdaily);            }            connection.close();        }catch (ClassNotFoundException e) {            e.printStackTrace();        } catch (SQLException e) {            e.printStackTrace();        }    }}