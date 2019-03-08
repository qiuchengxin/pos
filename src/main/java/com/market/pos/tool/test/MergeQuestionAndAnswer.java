package com.market.pos.tool.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MergeQuestionAndAnswer {

    public static String mergeQuestionAndAnswer(){
        //随机获得题目以及正确答案
        String jsonQuestion = CheckQuestionAndAnswer.checkQuestion();
        JSONObject jsonObjectQuestion = JSONObject.parseObject(jsonQuestion);
        int questionId = jsonObjectQuestion.getInteger("id");
        String questionContent = jsonObjectQuestion.getString("content");
        int answerId = jsonObjectQuestion.getInteger("answerId");

        //获得正确答案的对象
        String jsonRightAnswer = CheckQuestionAndAnswer.checkAnswer(questionId);
        JSONObject jsonObjectRightAnswer = JSONObject.parseObject(jsonRightAnswer);

        //获得错误答案 3个干扰选项
        String jsonWrongAnswer = CheckQuestionAndAnswer.chechWrongAnswers(questionId);
        JSONArray jsonArrayWrongAnswer = JSONArray.parseArray(jsonWrongAnswer);

        //整合正确答案和错误答案
        jsonArrayWrongAnswer.add(jsonObjectRightAnswer);

        //取随机
        String rank = CheckQuestionAndAnswer.cheackRank();
        JSONArray jsonArrayRank = JSONArray.parseArray(rank);
        //创造随机排序的答案
        JSONArray jsonArrayRankAnswer = new JSONArray();
        for (int i = 0; i < jsonArrayWrongAnswer.size() ; i++){
            JSONObject jsonObjectAnswer = jsonArrayWrongAnswer.getJSONObject(i);
            JSONObject jsonObjectRank = jsonArrayRank.getJSONObject(i);
            int quesId = jsonObjectAnswer.getInteger("questionId");
            int ansId = jsonObjectAnswer.getInteger("id");
            String content = jsonObjectAnswer.getString("content");
            int rankId = jsonObjectRank.getInteger("id");
            int optionNumber = jsonObjectRank.getInteger("option");
            String option = CheckQuestionAndAnswer.option(optionNumber);
            //插入新的JSONObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("questionId",quesId);
            jsonObject.put("answerId",ansId);
            jsonObject.put("content",content);
            jsonObject.put("rankId",rankId);
            jsonObject.put("option",option);
            jsonObject.put("testQuestionId",questionId);
            jsonObject.put("testQuestionContent",questionContent);
            jsonObject.put("testAnswerId",answerId);

            jsonArrayRankAnswer.add(jsonObject);
        }
        return jsonArrayRankAnswer.toJSONString();

    };
}
