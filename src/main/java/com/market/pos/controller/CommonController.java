package com.market.pos.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.pojo.TeamImpormember;
import com.market.pos.pojo.TeamList;
import com.market.pos.service.ITeamMembersImportService;
import com.market.pos.service.JPAService.TeamListRepository;
import com.market.pos.service.TargetService;
import com.market.pos.tool.lemoc.LemocWebSocketClientFactory;
import org.apache.log4j.Logger;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/common")
public class CommonController {

    private static Logger logger = Logger.getLogger(CommonController.class);

    @Autowired
    private ITeamMembersImportService iTeamMembersImportService;

    @Autowired
    private TargetService targetService;

    @Autowired
    private TeamListRepository teamListRepository;

    @RequestMapping("/ready")
    public void tuanQue(HttpServletRequest request) throws Exception {
        WebSocketClient lemocWebSocketClient = LemocWebSocketClientFactory.getLemocWebSocketClient();

        String text = request.getParameter("text");
        String tId = request.getParameter("tId");
        String target = request.getParameter("target");
        int id = Integer.parseInt(tId);
        TeamList teamlist =  teamListRepository.findById(id);
        String tName = teamlist.getTName();
        String tType = teamlist.getTType();
        String tTime = teamlist.getTTime();

        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("106");
        askQQMessage.setMsg("近期有副本安排如下，您已在排表中，请核对信息。" +
                "\n日期：" + tTime +
                "\n副本：" + tType + " " + tName +
                "\n确认参加请回复[1] , 不能参加本次副本请回复[2]");

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
                    //查询该用户是否答复过了，若已确认则不询问
                    String typeByUserId = targetService.findByUserId(userid,tId);
                    //如果为空，判定为第一次发给该用户确认信息
                    if (typeByUserId == null){
                        targetService.insertUserIdAndTarget(userid,target,0,tId);
                        logger.info("-----------发送信息给" + userid + "-----------");
                        askQQMessage.setQQID(userid);
                        String ask = new Gson().toJson(askQQMessage);
                        System.out.println(ask);
                        lemocWebSocketClient.send(ask);
                    }else {
                        if (!typeByUserId.equals("1")){
                            logger.info("-----------发送信息给" + userid + "-----------");
                            askQQMessage.setQQID(userid);
                            String ask = new Gson().toJson(askQQMessage);
                            System.out.println(ask);
                            lemocWebSocketClient.send(ask);
                        }else {
                            logger.info("userName : " + username + " -- 此用户已经确认过了");
                        }
                    }
                }else {
                    logger.info("匹配不上当前用户名：" + username);
                }
            }
        }else if (text == null){
            logger.info("---坐标:" + target + "---文本内容为空");
        }
    }
}
