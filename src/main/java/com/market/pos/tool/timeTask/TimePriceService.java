package com.market.pos.tool.timeTask;

import com.market.pos.controller.UserController;
import com.market.pos.pojo.TimePrice;
import com.market.pos.tool.connect.JdbcMoenyPrice;
import com.market.pos.tool.connect.JdbcUsers;

import java.util.List;

public class TimePriceService {

    /**
     *
     * 插入定时查询金价数据
     * @param moeney
     * @param time
     */
    public static void insertTimePrice(String moeney,String time){
        String sql = "insert into time_price (money,time) values (" +
                  "'" + moeney + "'" + ","
                + "'" + time + "'" + ")";
        JdbcUsers.insert(sql,"pay_data");
        System.out.println(sql);
    }

    public static Object searchMonryPrice(){
        String sql = "select * from time_price";
        JdbcMoenyPrice.searchCw(sql,"pay_data");
        TimePrice timePrice = new TimePrice();
        timePrice.setMoney(JdbcMoenyPrice.money);
        timePrice.setTime(JdbcMoenyPrice.time);
        System.out.println(JdbcMoenyPrice.money);
        return timePrice;
    }

    public static void main(String[] args) {
        Object a = searchMonryPrice();
        System.out.println(a);
    }
}
