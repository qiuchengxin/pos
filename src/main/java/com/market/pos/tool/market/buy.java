package com.market.pos.tool.market;

public class buy {
    public static String buy_data_end;

    /**
     * buy_data:购买者背包中物品的字符串，good:被交易的商品
     * @param buy_data
     * @param good
     */

    public static void buy(String buy_data,String good){
        if (buy_data == null){
            buy_data_end = good;
        }else {
            buy_data_end = buy_data + "," + good;
        }
    }
}
