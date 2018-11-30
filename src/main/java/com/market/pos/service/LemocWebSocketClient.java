package com.market.pos.service;import com.google.gson.Gson;import com.market.pos.controller.NLU;import com.market.pos.pojo.AskQQMessage;import com.market.pos.pojo.QQMessage;import com.market.pos.tool.DianZan;import com.market.pos.tool.QianDao;import com.market.pos.tool.pk.TiaoZhan;import org.java_websocket.client.WebSocketClient;import org.java_websocket.drafts.Draft;import org.java_websocket.handshake.ServerHandshake;import java.net.MalformedURLException;import java.net.URI;import java.util.regex.Pattern;public class LemocWebSocketClient extends WebSocketClient {    public  LemocWebSocketClient(URI serverUri, Draft draft) {        super(serverUri, draft);    }    public void onClose(int arg0, String arg1, boolean arg2) {        System.out.println("客户端关闭");    }    public void onError(Exception arg0) {        System.out.println("客户端错误");    }    public void onMessage(String json){        //信息处理        //将json字符串利用GSON转化成pojo对象,利用面向对象的方式处理qq信息        QQMessage receiveMessage = (QQMessage) new Gson().fromJson(json, QQMessage.class);        //获得qq信息来源的qq号以及qq信息的内容        String qqid = receiveMessage.getFromQQ();        String nick = receiveMessage.getNick();        String msg = receiveMessage.getMsg();        String groupid = receiveMessage.getFromGroup();        //传递信息到NLU        String qiandao = "签到";        if ((msg.equals(qiandao) == true)) {            QianDao.nluMod(qqid, nick, msg, groupid);            String ask = QianDao.ask;            send(ask);        }        String dianzhan = "赞我";        if ((msg.equals(dianzhan) == true)){            DianZan.dianZan(qqid,groupid);            String ask = DianZan.ask;            send(ask);            send(DianZan.ask_dianzhan);        }        String tiaozhan = "挑战\\u005BCQ:at,qq=.*";        Boolean ismatch = Pattern.matches(tiaozhan,msg);        System.out.println(ismatch);        if(ismatch == true){            TiaoZhan.tiaoZhan(qqid,groupid,msg);            String ask = TiaoZhan.ask;            send(ask);            System.out.println(ask);        }    }    public void onOpen(ServerHandshake arg0){        System.out.println("客户端启动");    }}