package com.market.pos.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.pojo.TeamImpormember;
import com.market.pos.pojo.TeamMembers;
import com.market.pos.service.ITeamMembersImportService;
import com.market.pos.service.TargetService;
import com.market.pos.tool.lemoc.LemocWebSocketClientFactory;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private ITeamMembersImportService iTeamMembersImportService;

    @Autowired
    private TargetService targetService;

    @RequestMapping("/ready")
    public void tuanQue(HttpServletRequest request) throws Exception {
        WebSocketClient lemocWebSocketClient = LemocWebSocketClientFactory.getLemocWebSocketClient();

        String text = request.getParameter("text");
        String tId = request.getParameter("tId");
        String target = request.getParameter("target");

        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("106");
        askQQMessage.setMsg("确认请回复[1] , 不能参加本次副本请回复[2]");

        List<TeamImpormember> list = iTeamMembersImportService.findAllByGroupid("721623673");
        String jsonString = JSON.toJSONString(list);
        JSONArray jsonArray = JSONArray.parseArray(jsonString);

        //非空才匹配
        if (text != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String username = jsonObject.getString("username");
                String userid = jsonObject.getString("userid");
                if (text.matches(".*" + username + ".*")) {
                    System.out.println("-----------发送信息给" + userid + "-----------");
                    askQQMessage.setQQID(userid);
                    String ask = new Gson().toJson(askQQMessage);
                    System.out.println(ask);
                    lemocWebSocketClient.send(ask);
                    //插入信息回复信息,type = 0
                    targetService.insertUserIdAndTarget(userid,target,0);
                }
            }
        }else if (text == null){
            System.out.println("---坐标:" + target + "---文本内容为空");
        }
    }
}
