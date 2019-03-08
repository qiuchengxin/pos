package com.market.pos.tool.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AskForTest {

    /**
     * 我要答题，新增数据
     * @param userid
     */
    public static void askForTest(String userid) {
        //插入新题目记录生成唯一ID
        CheckQuestionAndAnswer.insertTest(userid);

        String json = MergeQuestionAndAnswer.mergeQuestionAndAnswer();
        JSONArray jsonArray = JSONArray.parseArray(json);
        JSONObject jsonObjectFirst = jsonArray.getJSONObject(0);
        //正确答案的Id
        int testAnswerId = jsonObjectFirst.getInteger("testAnswerId");
        String answer = null;
        //题目的Id与文本内容
        int testQuestionId = jsonObjectFirst.getInteger("testQuestionId");
        String testQuestionContent = jsonObjectFirst.getString("testQuestionContent");
        //答案文本内容
        String a = null;
        String b = null;
        String c = null;
        String d = null;
        for (int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int answerId = jsonObject.getInteger("answerId");
            String option = jsonObject.getString("option");
            String content = jsonObject.getString("content");
            if (answerId == testAnswerId){
                answer = option;
            }
            if (option.equals("a")){
                a = content;
            }else if (option.equals("b")){
                b = content;
            }else if (option.equals("c")){
                c = content;
            }else if (option.equals("d")){
                d = content;
            }
        }
        //执行更新操作
        CheckQuestionAndAnswer.updateTest(userid,testQuestionId,testQuestionContent,answer,a,b,c,d);
    }

    /**
     * 答题请求生成随机试题
     * @param userid
     * @param groupid
     * @return
     */
    public static String AskToUser(String userid,String groupid){
        //用户说：“我要答题”，则生成一条题目数据
        askForTest(userid);

        //查询最新数据
        String json = CheckQuestionAndAnswer.searchTest(userid);
        JSONObject jsonObject = JSONObject.parseObject(json);
        String questionContent = jsonObject.getString("questionContent");
        String a = jsonObject.getString("a");
        String b = jsonObject.getString("b");
        String c = jsonObject.getString("c");
        String d = jsonObject.getString("d");

        //组装qq回复语句
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setQQID(userid);
        askQQMessage.setGroupid(groupid);
        askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 请听题：" +
                        "\n 【题目】：" + questionContent +
                        "\n（a）" + a +
                        "\n（b）" + b +
                        "\n（c）" + c +
                        "\n（d）" + d);
        String ask = new Gson().toJson(askQQMessage);
        //获取当前时间
        long date = System.currentTimeMillis();
        String time = Long.toString(date);
        CheckQuestionAndAnswer.updateTime(userid,time);
        return ask;
    }

    /**
     * 回复答案进行校验
     * @param userid
     * @param groupid
     * @param msg
     * @return
     */
    public static String answerByUser(String userid,String groupid,String msg) {
        //查询答案与is_right字段
        String json = CheckQuestionAndAnswer.searchTest(userid);
        JSONObject jsonObject = JSONObject.parseObject(json);
        String answer = jsonObject.getString("answer");
        int isRight = jsonObject.getInteger("isRight");
        String time = jsonObject.getString("time");
        //初始化回复语句
        String ask = null;
        //组装qq回复语句
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setQQID(userid);
        askQQMessage.setGroupid(groupid);
        //如果已经回答过了，则无视本次回答，不做回复处理
        if (isRight == 1){
            ask = "IGNORE";
        }else {
            //时间校验
            long nowDateLong = System.currentTimeMillis();
            long timeLong = Long.parseLong(time);
            long k = nowDateLong - timeLong;
          
            System.out.println(k);
            //超过了4秒钟未答出答案
            if (k > 4000){
                //进行禁言处理
            	System.out.println("-----禁言处理-----");
//                askQQMessage.setDuration("600000");
//                ask = new Gson().toJson(askQQMessage);
            }
            
            //如果答案正确
            if (msg.equals(answer)) {
            	askQQMessage.setMsg("[CQ:at,qq=" + userid + "]  恭喜您回答正确 ！ ");
            	ask = new Gson().toJson(askQQMessage);
            	//将is_right置为1，不被打扰
            	CheckQuestionAndAnswer.updateIsRight(userid);
            } else {
                askQQMessage.setMsg("[CQ:at,qq=" + userid + "]  回答错误哦。 ");
                ask = new Gson().toJson(askQQMessage);
            }
        }
        System.out.println(ask);
        return ask;
    }
}
