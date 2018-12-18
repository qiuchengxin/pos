package com.market.pos.tool.market;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.findTreasure.BackpackService;

public class SearchSell {
    public static String ask;

    public static void searchSell(String qqid,String groupid){
        //创建返回对象
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setQQID(qqid);
        askQQMessage.setGroupid(groupid);

        MarketService.selectMarketByUserid(qqid,groupid);
        String money = MarketService.money;
        String good = MarketService.good;
        String buy_userid = MarketService.buy_userid;
        String userid = MarketService.userid;

        if (userid == null){
            askQQMessage.setMsg("[CQ:at,qq=" +qqid+ "] 还没有上架商品 ！" +
                    "\n摆摊命令，例如：我要100金出售雪河套 。");
            ask = new Gson().toJson(askQQMessage);
        }else {
            if (buy_userid == null){
                askQQMessage.setMsg("[CQ:at,qq=" +qqid+ "] 您的东西没人买呀，是不是太磕碜了烙铁 ！" +
                        "\n如果没有顾客您可以尝试对我说“我不摆摊了” 然后重新上架商品，温馨提示：亲民的价格和受人喜爱的商品更容易达成交易哦 ！ ");
                ask = new Gson().toJson(askQQMessage);
            }else {
                askQQMessage.setMsg("[CQ:at,qq=" +qqid+ "] 恭喜您，商品已经被购买啦 ！" +
                        "\n交易单：玩家 [CQ:at,qq=" +buy_userid+ "] 以 " + money + " 金购买了您上架的 " + good +" ！祝贺！" +
                        "\n温馨提示：如果还要摆摊，记得和我说“我不摆摊了” 然后重新上架商品，温馨提示：亲民的价格和受人喜爱的商品更容易达成交易哦 ！");
                ask = new Gson().toJson(askQQMessage);
            }
        }
    }
}
