package com.market.pos.tool.market_equipment;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.pk.GetQid;

public class GodMarketBuy_equipment {
    public static String ask;

    public static void godMarketBuy(String buy_userid,String msg,String groupid){
        GetQid.getQid(msg);
        String sell_userid = GetQid.ch_qqid;

        SellController_equipment.sellController(sell_userid,buy_userid,groupid);
        int GodNum = SellController_equipment.GodNum;

        //创建返回对象
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setQQID(sell_userid);
        askQQMessage.setGroupid(groupid);

        if (GodNum == 2){
            askQQMessage.setMsg("[CQ:at,qq=" +buy_userid+ "] 您一分钱都没有，赶紧滚吧 ！ ");
            ask = new Gson().toJson(askQQMessage);
        }
        if (GodNum == 3){
            askQQMessage.setMsg("[CQ:at,qq=" +buy_userid+ "] 您的金币不足，洗洗睡，乖 ！ ");
            ask = new Gson().toJson(askQQMessage);
        }
        if (GodNum == 4){
            askQQMessage.setMsg("[CQ:at,qq=" +buy_userid+ "] 交易完成，清查下背包，确定商品和金币交易无误 ！" +
                    "\n查看背包命令：“我的背包” ");
            ask = new Gson().toJson(askQQMessage);
        }
        if (GodNum == 5){
            askQQMessage.setMsg("[CQ:at,qq=" +buy_userid+ "] 已经有人购买过了，无法为您下单，尝试购买其他商品把 ！ ");
            ask = new Gson().toJson(askQQMessage);
        }
        if (GodNum == 6){
            askQQMessage.setMsg("[CQ:at,qq=" +buy_userid+ "] 压根儿没这玩意儿卖，你吓唬谁，跟谁俩呢，山炮儿 ！ " +
                    "\n温馨提示：检查下是不是艾特错人了 ！");
            ask = new Gson().toJson(askQQMessage);
        }
    }
}
