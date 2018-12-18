package com.market.pos.tool.market;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;

public class DelSell {

    public static String ask;

    public static void delSell(String qqid,String groupid){
       MarketService.delSell(qqid,groupid);

        //创建返回对象
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setQQID(qqid);
        askQQMessage.setGroupid(groupid);

        askQQMessage.setMsg("[CQ:at,qq=" +qqid+ "] 删除上一条摆摊记录成功，你可以重新摆摊了 ！" +
                "\n如果没有顾客您可以尝试对我说“我不摆摊了” 然后重新上架商品，温馨提示：亲民的价格和受人喜爱的商品更容易达成交易哦 ！ ");
        ask = new Gson().toJson(askQQMessage);
    }
}
