package com.market.pos.tool.market;

import com.market.pos.pojo.Backpack;
import com.market.pos.tool.Equip.DeLEquip;
import com.market.pos.tool.findTreasure.BackpackService;

public class SellController {

    //根据GodNum决定输出
    public static int GodNum;
    /**
     * 销售商品总控制器
     */
    public static void sellController(String sell_userid,String buy_userid,String groupid){

//        数据Demo
//        qqid = "1218129325";
//        String good = "化功大法";
//        String buy_userid = "513232448";
//        String money = "10000";

        //查询是否表中有销售者的数据
        MarketService.selectMarketByUserid(sell_userid,groupid);
        String market_userid = MarketService.userid;
        String market_buy_userid = MarketService.buy_userid;
        System.out.println("marketuserid : "+market_userid);

        if (market_userid != null){

            MarketService.selectMarketByUserid(sell_userid,groupid);
            String good = MarketService.good;
            String money = MarketService.money;
            //查询销售者的背包
            BackpackService.searchBackPack(sell_userid,groupid);
            String sell_data_book = BackpackService.book;

                //判定交易是否可以完成
                if (market_buy_userid == null){
                    //没人购买，可以下单
                    //先判定购买者钱够不够
                    BackpackService.searchBackPack(buy_userid,groupid);
                    String buy_user_money_String = BackpackService.money;
                    System.out.println("buy_money : " + buy_user_money_String);
                    if (buy_user_money_String == null){
                        System.out.println("您一分钱都没有！");
                        GodNum = 2;
                    }else if (Integer.parseInt(buy_user_money_String) < Integer.parseInt(money)){
                        System.out.println("您的金币不足");
                        GodNum = 3;
                    }else {

                        //金币足够，交易可以完成
                        GodNum = 4;
                        //销售的商品是书籍
                        Sell.sell(sell_data_book,good);
                        //获取交易后的销售者数据
                        String end = Sell.end;
                        BackpackService.updateBackPack(sell_userid,"book",end,groupid);

                        //交易成功，销售者获得金币
                        BackpackService.searchBackPack(sell_userid,groupid);
                        String sell_user_money_String = BackpackService.money;
                        Integer end_sell_user_money = Integer.parseInt(sell_user_money_String) + Integer.parseInt(money);
                        BackpackService.updateBackPack(sell_userid,"money",end_sell_user_money.toString(),groupid);

                        //交易完成，购买者失去金币
                        Integer end_buy_user_money = Integer.parseInt(buy_user_money_String) - Integer.parseInt(money);
                        BackpackService.updateBackPack(buy_userid,"money",end_buy_user_money.toString(),groupid);

                        //交易完成，检查销售者装备栏，如果有装备此商品，则删除
                        DeLEquip.delEquip(sell_userid,"book",good,groupid);

                        //查询购买者的背包
                        BackpackService.searchBackPack(buy_userid,groupid);
                        String resultUserid = BackpackService.resultUserId;
                        String buy_data_book = BackpackService.book;
                        if (resultUserid == null){
                            System.out.println("if (resultUserid == null)");
                            //表中无购买者数据，先进行插入操作
                            BackpackService.insertBackPack(buy_userid,groupid);
                            if (buy_data_book == null){
                                //购买者没有秘籍
                                BackpackService.updateBackPack(buy_userid,"book",good,groupid);
                                //update购买者id
                                MarketService.updateBuy_userid(sell_userid,buy_userid,groupid);
                            }else {
                                //购买者包里已经有秘籍了
                                String last_book = buy_data_book + "," + good;
                                BackpackService.updateBackPack(buy_userid,"book",last_book,groupid);
                                //update购买者id
                                MarketService.updateBuy_userid(sell_userid,buy_userid,groupid);
                            }
                        }else {
                            System.out.println("if (resultUserid != null)");
                            //backpack表中已经有了购买者数据，直接进行更新操作
                            if (buy_data_book == null){
                                //购买者没有秘籍
                                BackpackService.updateBackPack(buy_userid,"book",good,groupid);
                                //update购买者id
                                MarketService.updateBuy_userid(sell_userid,buy_userid,groupid);
                            }else {
                                //购买者包里已经有秘籍了
                                String last_book = buy_data_book + "," + good;
                                BackpackService.updateBackPack(buy_userid,"book",last_book,groupid);
                                //update购买者id
                                MarketService.updateBuy_userid(sell_userid,buy_userid,groupid);
                            }
                        }
                    }
                }else {
                    System.out.println("已经有人购买过了，无法为您下单");
                    GodNum = 5;
                }
        }else {
            //没有上架上架商品
            System.out.println("无商品");
            GodNum = 6;
        }
    }
}
