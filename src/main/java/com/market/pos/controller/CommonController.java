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
import com.market.pos.service.TeamListService;
import com.market.pos.tool.connect.JdbcCreateTable;
import com.market.pos.tool.jpgTable.CreateTableController;
import com.market.pos.tool.jpgTable.GraphicsGeneration;
import com.market.pos.tool.lemoc.LemocWebSocketClientFactory;
import org.apache.log4j.Logger;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private TeamListService teamListService;

    @RequestMapping("/ready")
    public void tuanQue(HttpServletRequest request) throws Exception {
        WebSocketClient lemocWebSocketClient = LemocWebSocketClientFactory.getLemocWebSocketClient();

        String[] text = request.getParameterValues("text");
        String tId = request.getParameter("tId");
        String[] target = request.getParameterValues("targetId");
        int id = Integer.parseInt(tId);
        TeamList teamlist = teamListRepository.findById(id);
        String tName = teamlist.getTName();
        String tType = teamlist.getTType();
        String tTime = teamlist.getTTime();
        String t_id = teamlist.getTId();
        String liuyan = teamlist.getLiuyan();
        //jpgName命名为时间+ID
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String create_time = df.format(new Date());
        String jpgName = create_time + tId;
        //cellValue为排表的行列内容
        String sql = "select * from team_tree where t_id = " + "'" + t_id + "'" ;
        String[][] cellsValue = JdbcCreateTable.searchQA(sql,"pay_data");
        //组装title
        String title = tType + tName + " " + tTime;
        //组装留言
        String remark =" 留言：" + liuyan;
        //生成图片
        GraphicsGeneration.graphicsGeneration(cellsValue,title,remark,jpgName);
        //组装答复
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("106");
        askQQMessage.setMsg("近期有副本安排如下，您已在排表中，请核对信息。" +
                "\n日期：" + tTime +
                "\n副本：" + tType + " " + tName +
                "\n确认参加请回复[1] , 不能参加本次副本请回复[2]"+
                "\n[CQ:image,file=/team_table/" + jpgName + ".jpg]");

        List<TeamImpormember> list = iTeamMembersImportService.findAllByGroupid("721623673");
        String jsonString = JSON.toJSONString(list);
        JSONArray jsonArray = JSONArray.parseArray(jsonString);


        //遍历表格内容
        for (int i = 0; i < 25; i++) {
            //非空才匹配
            if (text[i] != null) {
                for (int j=0;j<jsonArray.size();j++){
                    JSONObject jsonObject = jsonArray.getJSONObject(j);
                    String username = jsonObject.getString("username");
                    String userid = jsonObject.getString("userid");
                    //若是匹配上了
                    if (text[i].matches(".*" + username + ".*")) {
                        //查询该用户是否答复过了，若已确认则不询问
                        String typeByUserId = targetService.findByUserId(userid, tId);
                        //如果为空，判定为第一次发给该用户确认信息
                        if (typeByUserId == null) {
                            logger.info("-----------发送信息给" + username + "(" + userid + ")" + "-----------");
                            askQQMessage.setQQID(userid);
                            String ask = new Gson().toJson(askQQMessage);
                            //插入数据
                            targetService.insertUserIdAndTarget(userid, target[i], 0, tId);
                            lemocWebSocketClient.send(ask);
                        } else { //若不为空，则校验是否已经回答过了
                            //如果未曾回复
                            if (!typeByUserId.equals("1")) {
                                logger.info("-----------发送信息给" + username + "(" + userid + ")" + "-----------");
                                askQQMessage.setQQID(userid);
                                String ask = new Gson().toJson(askQQMessage);
                                lemocWebSocketClient.send(ask);
                            } else {
                                logger.info("******* : " + username + " 已经确认过了*******");
                            }
                        }
                    }
                }
            }
        }
    }
}
