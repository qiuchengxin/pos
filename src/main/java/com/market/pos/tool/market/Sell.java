package com.market.pos.tool.market;

import com.market.pos.tool.connect.JdbcUsers;

public class Sell {

    /**
     * good:交易的商品
     * sell_data:卖家背包中的商品字符串
     * end: sell_data 扣除 good 后的字符串
     */
    public static String end;

    public static void sell(String sell_data , String good){
        if (sell_data.matches(".*,.*")){
            System.out.println("命中了逗号");
            if (sell_data.matches(".*," + good + ".*")){
                end = sell_data.replaceFirst(","+good,"");
                System.out.println(end);

            }else if (sell_data.matches("," + good +".*")){
                end = sell_data.replaceFirst("," + good,"");
                System.out.println(end);
            }else if (sell_data.matches(good + ",.*")){
                end = sell_data.replaceFirst(good + ",","");
                System.out.println(end);
            }
        }else if (sell_data.matches(good)){
            end = sell_data.replaceFirst(good,"");
            System.out.println(end);
        }else {
            end = sell_data;
            System.out.println(end);
        }
    }
}
