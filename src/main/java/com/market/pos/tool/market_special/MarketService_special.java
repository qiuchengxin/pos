package com.market.pos.tool.market_special;

import com.market.pos.tool.connect.JdbcUsers;
import com.market.pos.tool.market.JdbcMarket;

public class MarketService_special {
    public static String userid;
    public static String buy_userid;
    public static String money;
    public static String good;

    public static void selectMarketByUserid(String qqid,String groupid){
        String sql = "select * from market where userid = " + qqid;
        JdbcMarket.selectMarket(sql,groupid);
        userid = JdbcMarket.userid;
        buy_userid = JdbcMarket.buy_userid;
        money = JdbcMarket.money;
        good = JdbcMarket.good;
    }

    public static void insertUserid(String qqid,String groupid){
        String sql = "insert into market (userid) values (" + qqid + ")";
        JdbcUsers.insert(sql,groupid);
    }


    public static void updateBuy_userid(String sellUserid,String buyUserid,String groupid){
        String sql = "update market set buy_userid = " +
                buyUserid + " where userid = " +sellUserid;
        System.out.println(sql);
        JdbcUsers.update(sql,groupid);
    }

    public static void updateMoneyAndGood(String sellUserid, String money , String good,String groupid){
        String sql = "update market set money = " +
                money + ", good = " +
                "'" + good + "'" + " where userid = " +sellUserid;
        System.out.println(sql);
        JdbcUsers.update(sql,groupid);
    }

    public static void delSell(String sellUserid,String groupid){
        String sql = "delete from market where userid = " + sellUserid;
        JdbcUsers.update(sql,groupid);
        System.out.println("删除摆摊数据成功");
    }
}
