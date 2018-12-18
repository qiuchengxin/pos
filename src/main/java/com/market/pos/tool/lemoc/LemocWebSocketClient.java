package com.market.pos.tool.lemoc;


import com.google.gson.Gson;

import com.market.pos.pojo.AskQQMessage;
import com.market.pos.pojo.QQMessage;
import com.market.pos.tool.Equip.EquipIn;
import com.market.pos.tool.Equip.OpenEquip;
import com.market.pos.tool.Rank.RankController;
import com.market.pos.tool.WuxiaPk.Hurt;
import com.market.pos.tool.dujie.HelpDuJie;
import com.market.pos.tool.dujie.SkyController;
import com.market.pos.tool.dujie.Who;
import com.market.pos.tool.findTreasure.BackpackController;
import com.market.pos.tool.findTreasure.OpenBack;
import com.market.pos.tool.hideroom.FindHideRoom;
import com.market.pos.tool.hideroom.HideRoom;
import com.market.pos.tool.hideroom.HurtHideRoom;
import com.market.pos.tool.pk.GetQid;
import com.market.pos.tool.qiandao.DianZan;
import com.market.pos.tool.qiandao.QianDao;
import com.market.pos.tool.pk.TiaoZhan;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;


import java.net.URI;
import java.util.regex.Pattern;

public class LemocWebSocketClient extends WebSocketClient {

    public  LemocWebSocketClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public void onClose(int arg0, String arg1, boolean arg2) {
        System.out.println("客户端关闭");
    }

    public void onError(Exception arg0) {
        System.out.println("客户端错误");
    }

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
        System.out.println(subType);
        //传递信息到NLU

        String qiandao = "签到";
        if ((msg.equals(qiandao) == true) || msg.equals("qd") == true || msg.equals("QD") == true) {
            QianDao.nluMod(qqid, nick, msg, groupid);
            String ask = QianDao.ask;
            send(ask);
        }

        String dianzhan = "赞我";
        if ((msg.equals(dianzhan) == true)){
            DianZan.dianZan(qqid,groupid);
            String ask = DianZan.ask;
            send(ask);
            send(DianZan.ask_dianzhan);
        }

        String tiaozhan = "挑战\\u005BCQ:at,qq=.*";
        Boolean ismatch = Pattern.matches(tiaozhan,msg);
        System.out.println(ismatch);
        if(ismatch == true){
            TiaoZhan.tiaoZhan(qqid,groupid,msg);
            String ask = TiaoZhan.ask;
            send(ask);
            System.out.println(ask);
        }

        if (msg.equals("挖宝") == true){
            BackpackController.backpackController(qqid,groupid);
            String ask = BackpackController.ask;
            send(ask);
            System.out.println(ask);
        }

        String  openback = "我的背包";
        if (msg.equals(openback) == true){
            OpenBack.openBack(qqid,groupid);
            String ask = OpenBack.ask;
            send(ask);
            System.out.println(ask);
        }

        if (msg.matches("我要修炼.*")){
            GetQid.getMsgb(msg);
            String msg_use = GetQid.ch_msg;
            EquipIn.BookIn(qqid,groupid,msg_use);
            String ask = EquipIn.ask;
            send(ask);
            System.out.println(ask);
        }

        if (msg.matches("我要装备.*")){
            GetQid.getMsge(msg);
            String msg_use = GetQid.ch_msg;
            EquipIn.equipmentIn(qqid,groupid,msg_use);
            String ask = EquipIn.ask;
            send(ask);
            System.out.println(ask);
        }

        if(msg.matches(".*毛线.*装备.*") || msg.matches("我的装备")){
            OpenEquip.openEquip(qqid,groupid);
            String ask = OpenEquip.ask;
            send(ask);
            System.out.println(ask);
        }

        if (msg.matches("\\u005BCQ:at,qq=.*用.*") || msg.matches("用.*\\u005BCQ:at,qq=.*")){
            GetQid.getQid(msg);
            Hurt.hurt(qqid,groupid,msg);
            String ask = Hurt.ask;
            send(ask);
            System.out.println(ask);
        }


        if (subType.equals("1") && msg.matches(".*要.*闭关.*")){
            AskQQMessage askQQMessage = new AskQQMessage();
            askQQMessage.setAct("101");
            askQQMessage.setGroupid(groupid);
            askQQMessage.setMsg("   收声了弟弟！想闭关修炼就尝试私聊我吧！" +
                    "\n温馨提示：闭关成功可获得大量修为，同样如果在闭关期间侠士被其他玩家偷袭成功，则前功尽弃，修为大损！" +
                    "\n命令“偷袭@xxx 在对方闭关时可取的意想不到的效果！”");
            String ask = new Gson().toJson(askQQMessage);
            send(ask);
        }

        if (!subType.equals("1") && msg.matches("我要在.*闭关")){
            GetQid.getGroupId(msg);
            String belongId = GetQid.belongId;
            HideRoom.hideRoom(qqid,belongId);
            String ask = HideRoom.ask;
            send(ask);
            System.out.println(ask);
        }

        if (!subType.equals("1") && msg.matches("闭关")){
            AskQQMessage askQQMessage = new AskQQMessage();
            askQQMessage.setAct("106");
            askQQMessage.setQQID(qqid);
            askQQMessage.setMsg("温馨提示：闭关系统分群处理啦，目前开放的群有：皓水群（514869445）、帮会群（106102978）" +
                    "\n例如您要在皓水群闭关，请说：【我要在皓水群闭关】，句式：我要在xxx闭关 ’ ");
            String ask = new Gson().toJson(askQQMessage);
            send(ask);
        }

        if (subType.equals("1") && msg.matches(".*偷袭.*\\u005BCQ:at,qq=.*")){
            GetQid.getQid(msg);
            String be_qqid = GetQid.ch_qqid;
            HurtHideRoom.hurtHideRoom(qqid,be_qqid,groupid);
            String ask = HurtHideRoom.ask;
            send(ask);
            System.out.println(ask);
        }

        if (msg.matches(".*谁在闭关.*") || msg.matches(".*偷偷修炼.*") || msg.matches(".*偷偷闭关.*")){
            FindHideRoom.findHideRoom(groupid);
            String ask = FindHideRoom.ask;
            send(ask);
            System.out.println(ask);
        }

        if (msg.matches(".*看大佬.*") || msg.matches(".*天字榜.*")){
            RankController.rankAll(qqid,groupid);
            String ask = RankController.ask;
            send(ask);
            System.out.println(ask);
        }

        if (msg.matches(".*我要渡劫.*")){
            SkyController.skyController(qqid,groupid);
            String ask = SkyController.ask;
            send(ask);
            System.out.println(ask);
        }

        if (msg.matches(".*我要帮助.*\\u005BCQ:at,qq=.*")){
            GetQid.getQid(msg);
            String userid = GetQid.ch_qqid;
            HelpDuJie.helpDuJie(qqid,userid,groupid);
            String ask = HelpDuJie.ask;
            send(ask);
            System.out.println(ask);
        }

        if (msg.matches(".*\\u005BCQ:at,qq=.*是破坏者.*")){
            Who.who(qqid,msg,groupid);
            String ask = Who.ask;
            send(ask);
            System.out.println(ask);
        }
    }

    public void onOpen(ServerHandshake arg0){
        System.out.println("客户端启动");
    }
}
