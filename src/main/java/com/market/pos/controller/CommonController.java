package com.market.pos.controller;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.lemoc.LemocWebSocketClient;
import com.market.pos.tool.lemoc.LemocWebSocketClientFactory;
import org.apache.ibatis.jdbc.Null;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/common")
public class CommonController {

//    private LemocWebSocketClient lemocWebSocketClient;
    
    @RequestMapping("/ready")
    public static String tuanQue(HttpServletRequest request) throws Exception {
        String text = request.getParameter("text");
        String tId = request.getParameter("tId");
        String target = request.getParameter("target");
        System.out.println(text);
        System.out.println(tId);
        System.out.println(target);
        System.out.println("-----------进入逻辑,构造通讯对象lemocWebSocketClient-----------");
        WebSocketClient lemocWebSocketClient = LemocWebSocketClientFactory.getLemocWebSocketClient();
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid("885890758");
        askQQMessage.setMsg("确认请回复[1] , 不能参加本次副本请回复[2]");
        String ask = new Gson().toJson(askQQMessage);
        System.out.println(ask);
        lemocWebSocketClient.send(ask);
        return ask;
    }
}
