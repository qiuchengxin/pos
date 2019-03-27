package com.market.pos.tool.lemoc;


import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.pojo.QQMessage;
import com.market.pos.tool.DailySearch.DailySearchController;
import com.market.pos.tool.Equip.EquipIn;
import com.market.pos.tool.Equip.OpenEquip;
import com.market.pos.tool.Rank.RankController;
import com.market.pos.tool.Rank.RankMin;
import com.market.pos.tool.TeamAdmin.TeamAdminController;
import com.market.pos.tool.TeamAdmin.TeamAdminService;
import com.market.pos.tool.TeamAdmin.TeamJoin;
import com.market.pos.tool.WuxiaPk.Hurt;
import com.market.pos.tool.cw.CwController;
import com.market.pos.tool.cw.CwService;
import com.market.pos.tool.cw.MakeCwController;
import com.market.pos.tool.dujie.HelpDuJie;
import com.market.pos.tool.dujie.SkyController;
import com.market.pos.tool.dujie.Who;
import com.market.pos.tool.findTreasure.BackpackController;
import com.market.pos.tool.findTreasure.OpenBack;
import com.market.pos.tool.goldPriceSearch.ServerSearch;
import com.market.pos.tool.hideroom.FindHideRoom;
import com.market.pos.tool.hideroom.HideRoom;
import com.market.pos.tool.hideroom.HurtHideRoom;
import com.market.pos.tool.hong.HongSearch;
import com.market.pos.tool.hong.PublicHongSearch;
import com.market.pos.tool.jpgTable.CreateTableController;
import com.market.pos.tool.market.*;
import com.market.pos.tool.market_equipment.GodMarketBuy_equipment;
import com.market.pos.tool.market_equipment.GodMarketSell_equipment;
import com.market.pos.tool.market_special.GodMarketBuy_special;
import com.market.pos.tool.market_special.GodMarketSell_special;
import com.market.pos.tool.pk.GetQid;
import com.market.pos.tool.pk.TiaoZhan;
import com.market.pos.tool.qa.QaService;
import com.market.pos.tool.qiandao.DianZan;
import com.market.pos.tool.qiandao.QianDao;
import com.market.pos.tool.qiyu.GetHttpClient;
import com.market.pos.tool.qiyu.QiYUController;
import com.market.pos.tool.serverOpenSearch.AdSearch;
import com.market.pos.tool.serverOpenSearch.ServerOpenSearch;
import com.market.pos.tool.test.AskForTest;
import com.market.pos.tool.tuanQue.TuanQue;
import com.market.pos.tool.wujiaHeZi.GetHeziList;
import org.apache.log4j.Logger;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.util.regex.Pattern;

public class LemocWebSocketClient extends WebSocketClient {

    private static Logger logger = Logger.getLogger(LemocWebSocketClient.class);

    public  LemocWebSocketClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    @Override
    public void onClose(int arg0, String arg1, boolean arg2) {
        logger.error("---CLOSE--- 客户端关闭");
    }

    @Override
    public void onError(Exception arg0) {
        logger.error("---ERROR---  客户端错误");
    }

    @Override
    public void onMessage(String json){
        //信息处理
        //将json字符串利用GSON转化成pojo对象,利用面向对象的方式处理qq信息
        QQMessage receiveMessage = (QQMessage) new Gson().fromJson(json, QQMessage.class);
        //获得qq信息来源的qq号以及qq信息的内容
        String qqid = receiveMessage.getFromQQ();
        String nick = receiveMessage.getNick();
        String msg = receiveMessage.getMsg();
        String groupid = receiveMessage.getFromGroup();
        String subType = receiveMessage.getSubType();
        String username = receiveMessage.getUsername();
        logger.info("【接收消息】" + nick +"(" + username + ")说 ："  + msg);
        //传递信息到NLU
        if (!subType.equals("1") && (msg.equals("1") || msg.equals("2"))){
            String ask = TuanQue.tuanQue(msg,qqid);
            send(ask);
        }
        if (groupid.matches("721623673") || groupid.matches("921340922")){

            if (msg.equals("我要答题")){
                String ask = AskForTest.AskToUser(qqid,groupid);
                send(ask);
            }

            if (msg.equals("a") || msg.equals("b") || msg.equals("c") || msg.equals("d")){
                String ask = AskForTest.answerByUser(qqid,groupid,msg);
                if (ask.equals("IGNORE")){
                    logger.info("--------------IGNORE *无视本次答题！*-------------");
                }else {
                    send(ask);
                }
            }

            if (msg.matches("我的.*宏")){
                String ask = HongSearch.HongSearch(qqid,msg,groupid);
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }else if (msg.matches(".*宏") && !msg.matches("我的.*宏")){
                String ask = PublicHongSearch.HongSearch(qqid,msg,groupid);
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }else if (msg.matches(".*有团吗.*")){
                String ask = TeamAdminController.searchTeamList(groupid,qqid);
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }else if (msg.matches("我要报名.*")){
                String ask = TeamAdminController.insertTeamMembers(groupid,qqid,username,msg,nick);
                send(ask);
                logger.info("【发送消息】 ：" +ask);

                String t_from = null;
                if (groupid.equals("721623673")){
                    t_from = "皓水";
                }else if (groupid.equals("921340922")){
                    t_from = "风波渡";
                }
                TeamAdminService.searchTeamList("pay_data",t_from);
                String t_id = TeamAdminService.t_id;
                GetQid.getUserType(msg);
                String usertype = GetQid.ch_msg;
                String askJoin = TeamJoin.teamJoin(t_id,qqid,usertype,groupid);
                send(askJoin);
                logger.info(askJoin);
            }else if (msg.matches(".*取消报名.*")){
                String ask = TeamAdminController.delTeamMembers(qqid,groupid);
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }else if (msg.matches(".*看排表.*")) {
                String t_from = null;
                if (groupid.equals("721623673")){
                    t_from = "hs";
                }else if (groupid.equals("921340922")){
                    t_from = "fbd";
                }
                //0321更新为图片输出
                try {
                    String ask = CreateTableController.setJpg(groupid,qqid);
                    send(ask);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(msg.matches(".*日常.*")){
                String ask = DailySearchController.dailySearch("pay_data",qqid,groupid);
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }else if (msg.matches("开服查询.*")){
                String ask = ServerOpenSearch.serverOpenSearchAnswers(msg,groupid);
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }else if (msg.matches("宠物查询.*")){
                try {
                    String ans = "你也可以对我说“CD宠物查询+服务器” 来查询刚进入CD的宠物~\n";
                    String ask = QiYUController.qyAnswers(msg,1,groupid,"宠物查询",ans);
                    send(ask);
                    logger.info(ask);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (msg.matches("CD宠物查询.*") || msg.matches("cd宠物查询.*")){
                try {
                    String ans = "【刚进CD的宠物】\n若想查询失联的宠物，请对我说“失联宠物查询+服务器”\n";
                    String ask = QiYUController.qyAnswers(msg,0,groupid,"CD宠物查询",ans);
                    send(ask);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (msg.matches("失联宠物查询.*")){
                try {
                    String ans = "【失联宠物】\n";
                    String ask = QiYUController.qyAnswers(msg,2,groupid,"失联宠物查询",ans);
                    send(ask);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (msg.matches("我的奇遇.*") || msg.matches("绝世奇遇.*")||msg.matches("所有绝世奇遇")){
                String ask = GetHttpClient.getAsk(msg,groupid);
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }
            else {
                String answer = QaService.qaSearch(msg);
                if (answer != null){
                    AskQQMessage askQQMessage = new AskQQMessage();
                    askQQMessage.setAct("101");
                    askQQMessage.setQQID(qqid);
                    askQQMessage.setGroupid(groupid);
                    askQQMessage.setMsg(answer);
                    String ask = new Gson().toJson(askQQMessage);
                    send(ask);
                }
            }

        }else if (!groupid.matches("721623673") && !groupid.matches("921340922")) {
            String qiandao = "签到";
            if ((msg.equals(qiandao) == true) || msg.equals("qd") == true || msg.equals("QD") == true) {
                QianDao.nluMod(qqid, username, msg, groupid);
                String ask = QianDao.ask;
                send(ask);
            }
            else if (msg.matches(".*日常.*")){
                String ask = DailySearchController.dailySearch("pay_data",qqid,groupid);
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }
            else if ((msg.equals("赞我") == true)) {
                DianZan.dianZan(qqid, groupid);
                String ask = DianZan.ask;
                send(ask);
                send(DianZan.ask_dianzhan);
            }

            String tiaozhan = "挑战\\u005BCQ:at,qq=.*";
            Boolean ismatch = Pattern.matches(tiaozhan, msg);
            logger.info(ismatch);
            if (ismatch == true) {
                TiaoZhan.tiaoZhan(qqid, groupid, msg);
                String ask = TiaoZhan.ask;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.equals("挖宝") == true) {
                BackpackController.backpackController(qqid, groupid);
                String ask = BackpackController.ask;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.equals("我的背包") == true) {
                OpenBack.openBack(qqid, groupid);
                String ask = OpenBack.ask;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.matches("我要修炼.*")) {
                GetQid.getMsgb(msg);
                String msg_use = GetQid.ch_msg;
                EquipIn.BookIn(qqid, groupid, msg_use);
                String ask = EquipIn.ask;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.matches("我要装备.*")) {
                GetQid.getMsge(msg);
                String msg_use = GetQid.ch_msg;
                EquipIn.equipmentIn(qqid, groupid, msg_use);
                String ask = EquipIn.ask;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.matches(".*毛线.*装备.*") || msg.matches("我的装备")) {
                OpenEquip.openEquip(qqid, groupid);
                String ask = OpenEquip.ask;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.matches("\\u005BCQ:at,qq=.*用.*") || msg.matches("用.*\\u005BCQ:at,qq=.*")) {
                GetQid.getQid(msg);
                Hurt.hurt(qqid, groupid, msg);
                String ask = Hurt.ask;
                String cwAsk = Hurt.cwAsk;
                CwService.searchCw(qqid, groupid);
                int cd = CwService.cd;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
                if (!cwAsk.matches("nocd") && cd == 0) {
                    send(cwAsk);
                    logger.info(cwAsk);
                    CwService.updateCd(qqid, groupid);
                }
            }

            else if (subType.equals("1") && msg.matches(".*要.*闭关.*")) {
                AskQQMessage askQQMessage = new AskQQMessage();
                askQQMessage.setAct("101");
                askQQMessage.setGroupid(groupid);
                askQQMessage.setMsg("   收声了弟弟！想闭关修炼就尝试私聊我吧！" +
                        "\n温馨提示：闭关成功可获得大量修为，同样如果在闭关期间侠士被其他玩家偷袭成功，则前功尽弃，修为大损！" +
                        "\n命令“偷袭@xxx 在对方闭关时可取的意想不到的效果！”");
                String ask = new Gson().toJson(askQQMessage);
                send(ask);
            }

            else if (!subType.equals("1") && msg.matches("我要在.*闭关")) {
                GetQid.getGroupId(msg);
                String belongId = GetQid.belongId;
                HideRoom.hideRoom(qqid, belongId);
                String ask = HideRoom.ask;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (!subType.equals("1") && msg.matches("闭关")) {
                logger.info("发起闭关请求");
                AskQQMessage askQQMessage = new AskQQMessage();
                askQQMessage.setAct("106");
                askQQMessage.setQQID(qqid);
                askQQMessage.setMsg("温馨提示：闭关系统分群处理啦，目前开放的群有：皓水群（514869445）、帮会群（106102978）、大锤群（258512073）、修真群（571175444）、狗带群（546665366）" +
                        "\n例如您要在皓水群闭关，请说：【我要在皓水群闭关】，句式：我要在xxx闭关 ’ ");
                String ask = new Gson().toJson(askQQMessage);
                send(ask);
            }

            else if (subType.equals("1") && msg.matches(".*偷袭.*\\u005BCQ:at,qq=.*")) {
                GetQid.getQid(msg);
                String be_qqid = GetQid.ch_qqid;
                HurtHideRoom.hurtHideRoom(qqid, be_qqid, groupid);
                String ask = HurtHideRoom.ask;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.matches(".*谁在闭关.*") || msg.matches(".*偷偷修炼.*") || msg.matches(".*偷偷闭关.*")) {
                FindHideRoom.findHideRoom(groupid);
                String ask = FindHideRoom.ask;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.matches(".*看大佬.*") || msg.matches(".*天字榜.*")) {
                RankController.rankAll(qqid, groupid);
                String ask = RankController.ask;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.matches(".*看弟弟.*") || msg.matches(".*弟弟排行.*")) {
                RankMin.rankMin(qqid, groupid);
                String ask = RankMin.ask;
                send(ask);
            }

            else if (msg.matches(".*我要渡劫.*")) {
                SkyController.skyController(qqid, groupid);
                String ask = SkyController.ask;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.matches(".*我要帮助.*\\u005BCQ:at,qq=.*")) {
                GetQid.getQid(msg);
                String userid = GetQid.ch_qqid;
                HelpDuJie.helpDuJie(qqid, userid, groupid);
                String ask = HelpDuJie.ask;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.matches(".*\\u005BCQ:at,qq=.*是破坏者.*")) {
                Who.who(qqid, msg, groupid);
                String ask = Who.ask;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.matches(".*做橙武.*") || msg.matches(".*做cw.*") || msg.matches(".*做CW.*")) {
                CwController.makeCw(qqid, groupid);
                String ask = CwController.ask;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.matches("我要锻造.*")) {
                MakeCwController.makeCwController(qqid, msg, groupid);
                String ask = MakeCwController.ask;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.matches(".*\\u005BCQ:at,qq=.*我要购买.*")) {
                GetQid.getQid(msg);
                String ch_qqid = GetQid.ch_qqid;
                MarketService.selectMarketByUserid(ch_qqid, groupid);
                String good = MarketService.good;
                if (ch_qqid == null) {
                    AskQQMessage askQQMessage = new AskQQMessage();
                    askQQMessage.setAct("101");
                    askQQMessage.setQQID(ch_qqid);
                    askQQMessage.setGroupid(groupid);
                    askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 压根儿没这玩意儿卖，你吓唬谁，跟谁俩呢，山炮儿 ！ \n温馨提示：检查下是不是艾特错人了 ！");
                    String ask = new Gson().toJson(askQQMessage);
                    this.send(ask);
                } else {
                    if (good.matches(".*套.*")) {
                        GodMarketBuy_equipment.godMarketBuy(qqid, msg, groupid);
                        String ask = GodMarketBuy_equipment.ask;
                        this.send(ask);
                    } else if (good.matches(".*玄晶.*")) {
                        GodMarketBuy_special.godMarketBuy(qqid, msg, groupid);
                        String ask = GodMarketBuy_special.ask;
                        this.send(ask);
                    } else if (!good.matches(".*套.*") && !good.matches(".*玄晶.*")) {
                        GodMarketBuy.godMarketBuy(qqid, msg, groupid);
                        String ask = GodMarketBuy.ask;
                        this.send(ask);
                    }
                }
            }

            else  if (msg.matches(".*我要.*出.*") && !msg.matches(".*套.*") && !msg.matches(".*玄晶.*")) {
                GodMarketSell.godMarketSell(qqid, msg, groupid);
                String ask = GodMarketSell.ask;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.matches(".*我要.*出.*套")) {
                GodMarketSell_equipment.godMarketSell(qqid, msg, groupid);
                String ask = GodMarketSell_equipment.ask;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.matches(".*我要.*出醉月玄晶")) {
                GodMarketSell_special.godMarketSell(qqid, msg, groupid);
                String ask = GodMarketSell_special.ask;
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.matches(".*我的商店.*")) {
                SearchSell.searchSell(qqid, groupid);
                String ask = SearchSell.ask;
                this.send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.matches(".*我不摆摊了.*")) {
                DelSell.delSell(qqid, groupid);
                String ask = DelSell.ask;
                this.send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.matches(".*金价查询.*")) {
                String ask = ServerSearch.sercerSearch(msg, groupid);
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }
            else if (msg.matches(".*更新公告.*")) {
                String ask = AdSearch.adSearch(groupid);
                send(ask);
            }
            else if (msg.matches("物价查询.*")) {
                String ask = GetHeziList.getHeziList(qqid, msg);
                send(ask);

                AskQQMessage askQQMessage = new AskQQMessage();
                askQQMessage.setAct("101");
                askQQMessage.setQQID(qqid);
                askQQMessage.setGroupid(groupid);
                askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 已将5173首页价格一览私聊发送给您，请查收 ！");
                String askGroup = new Gson().toJson(askQQMessage);
                send(askGroup);
            }

            else if (msg.matches(".*金价表.*")) {
                AskQQMessage askQQMessage = new AskQQMessage();
                askQQMessage.setAct("101");
                askQQMessage.setQQID(qqid);
                askQQMessage.setGroupid(groupid);
                askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 请点击：http://182.254.189.186:8081/list");
                String ask = new Gson().toJson(askQQMessage);
                send(ask);
            }

            else if (msg.matches(".*金价图.*")) {
                AskQQMessage askQQMessage = new AskQQMessage();
                askQQMessage.setAct("101");
                askQQMessage.setQQID(qqid);
                askQQMessage.setGroupid(groupid);
                askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 请点击：http://182.254.189.186:8080/echarts");
                String ask = new Gson().toJson(askQQMessage);
                send(ask);
            }

            else if (msg.matches("我的.*宏")){
                String ask = HongSearch.HongSearch(qqid,msg,groupid);
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else  if (msg.matches(".*宏") && !msg.matches("我的.*宏")){
                String ask = PublicHongSearch.HongSearch(qqid,msg,groupid);
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }

            else if (msg.equals("我要答题")){
                String ask = AskForTest.AskToUser(qqid,groupid);
                send(ask);
            }

            else if (msg.equals("a") || msg.equals("b") || msg.equals("c") || msg.equals("d")){
                String ask = AskForTest.answerByUser(qqid,groupid,msg);
                if (ask.equals("IGNORE")){
                    logger.info("--------------IGNORE *无视本次答题！*-------------");
                }else {
                    send(ask);
                }
            }
            else if (msg.matches("宠物查询.*")){
                try {
                    String ans = "你也可以对我说“CD宠物查询+服务器” 来查询刚进入CD的宠物~\n";
                    String ask = QiYUController.qyAnswers(msg,1,groupid,"宠物查询",ans);
                    send(ask);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (msg.matches("CD宠物查询.*") || msg.matches("cd宠物查询.*")){
                try {
                    String ans = "【刚进CD的宠物】\n若想查询失联的宠物，请对我说“失联宠物查询+服务器”\n";
                    String ask = QiYUController.qyAnswers(msg,0,groupid,"CD宠物查询",ans);
                    send(ask);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (msg.matches("失联宠物查询.*")){
                try {
                    String ans = "【失联宠物】\n";
                    String ask = QiYUController.qyAnswers(msg,2,groupid,"失联宠物查询",ans);
                    send(ask);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (msg.matches("我的奇遇.*") || msg.matches("绝世奇遇.*")||msg.matches("所有绝世奇遇")){
                String ask = GetHttpClient.getAsk(msg,groupid);
                send(ask);
                logger.info("【发送消息】 ：" +ask);
            }
            else {
                //QA兜底
                String answer = QaService.qaSearch(msg);
                if (answer != null){
                    AskQQMessage askQQMessage = new AskQQMessage();
                    askQQMessage.setAct("101");
                    askQQMessage.setQQID(qqid);
                    askQQMessage.setGroupid(groupid);
                    askQQMessage.setMsg(answer);
                    String ask = new Gson().toJson(askQQMessage);
                    send(ask);
                }
            }
        }
    }

    @Override
    public void onOpen(ServerHandshake arg0){
        logger.info("客户端启动");
    }
}
