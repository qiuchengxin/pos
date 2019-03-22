package com.market.pos.tool.market;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.findTreasure.BackpackService;
import com.market.pos.tool.pk.GetQid;

public class GodMarketSell {
    public static String ask;

    public static void godMarketSell(String qqid,String msg,String groupid){

        //创建返回对象
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setQQID(qqid);
        askQQMessage.setGroupid(groupid);

        //发起摆摊请求，插入商人userid
        MarketService.selectMarketByUserid(qqid,groupid);
        String resultUserid = MarketService.userid;
        if (resultUserid != null){
            System.out.println("您已经在摆摊了，等人来购买把，如果没有顾客您可以尝试对我说“我不摆摊了 ！”");
            askQQMessage.setMsg("[CQ:at,qq=" +qqid+ "] 您已经在摆摊了，等人来购买把 ！" +
                    "\n如果没有顾客您可以尝试对我说“我不摆摊了 ！”然后重新上架商品，温馨提示：亲民的价格和受人喜爱的商品更容易达成交易哦 ！ ");
            ask = new Gson().toJson(askQQMessage);
        }else {
            GetQid.getQid(msg);
            String money = GetQid.ch_qqid;
            GetQid.getSellGood(msg);
            String good = GetQid.good;
            System.out.println(money);
            System.out.println(good);

            BackpackService.searchBackPack(qqid,groupid);
            String sell_user_book = BackpackService.book;
            if (sell_user_book == null){
                askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 您包里没有这件商品哦，您是想空手套白狼吗 ~" +
                        "\n温馨提示：请检查您的背包再进行出售把 ！ ");
                ask = new Gson().toJson(askQQMessage);
            }else if (sell_user_book.matches(".*" + good + ".*") && sell_user_book != null) {
                askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 摆摊成功，请耐心等待傻大个儿来购买吧 ！" +
                        "\n如果没有顾客您可以尝试对我说“我不摆摊了 ！”然后重新上架商品，温馨提示：亲民的价格和受人喜爱的商品更容易达成交易哦 ！ ");
                ask = new Gson().toJson(askQQMessage);
                MarketService.insertUserid(qqid,groupid);
                MarketService.updateMoneyAndGood(qqid, money, good,groupid);
            }else {
                askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 您包里没有这件商品哦，您是想空手套白狼吗 ~" +
                        "\n温馨提示：请检查您的背包再进行出售把 ！ ");
                ask = new Gson().toJson(askQQMessage);
            }
        }
    }
}
