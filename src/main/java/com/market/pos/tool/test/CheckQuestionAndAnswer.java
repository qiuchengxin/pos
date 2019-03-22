package com.market.pos.tool.test;

import com.market.pos.tool.connect.JdbcTest;

public class CheckQuestionAndAnswer {

    /**
     * 获得题目信息，id + answerId + content
     * @return
     */
    public static String checkQuestion(){
        String sql = "SELECT * from question ORDER BY RAND() LIMIT 1";
        String json = JdbcTest.searchQuestion(sql,"pay_data");
        return json;
    }

    /**
     * 获得正确答案信息
     * @param questionId
     * @return
     */
    public static String checkAnswer(int questionId){
        String sql = "SELECT * from answer where question_id = " + "'" + questionId + "'";
        String json = JdbcTest.searchAnswer(sql,"pay_data");
        return json;
    }

    /**
     * 查询错误的三个答案
     * @param questionId
     * @return
     */
    public static String chechWrongAnswers(int questionId){
        String sql = "SELECT * from answer where question_id != " + "'" + questionId + "'" + " ORDER BY RAND() LIMIT 3 ";
        String jsonArray = JdbcTest.searchWrongAnswers(sql,"pay_data");
        return jsonArray;
    }

    public static String cheackRank(){
        String sql = "SELECT * from OPTIONS ORDER BY RAND()";
        String jsonArray = JdbcTest.searchRank(sql,"pay_data");
        return jsonArray;
    }

    /**
     * 新增题目
     * @param userid
     */
    public static void insertTest(String userid){
        String sql = "INSERT into test_list (userid) VALUES (" + "'" + userid + "'" + ")";
        JdbcTest.insertTest(sql,"pay_data");
    }

    public static void updateTest(String userid,int question_id,String question_content,String answer,String a,String b,String c,String d){
        String sql = "update test_list SET "
                + "question_id = " + "'" + question_id + "',"
                + "question_content = " + "'" + question_content + "',"
                + "answer = " + "'" + answer + "',"
                + "a = " + "'" + a + "',"
                + "b = " + "'" + b + "',"
                + "c = " + "'" + c + "',"
                + "d = " + "'" + d + "'"
                + " where userid = " + "'" + userid +"'" + " ORDER BY id DESC LIMIT 1";
        JdbcTest.insertTest(sql,"pay_data");
    }

    public static void updateIsRight(String userid){
        String sql = "update test_list SET is_right = 1 where userid = "+"'" + userid + "'"+" ORDER BY id DESC LIMIT 1";
        JdbcTest.insertTest(sql,"pay_data");
    }

    //更新答题开始时间
    public static void updateTime(String userid,String time){
        String sql = "update test_list SET time = " + "'" + time + "'" + " where userid = "+"'" + userid + "'"+" ORDER BY id DESC LIMIT 1";
        JdbcTest.insertTest(sql,"pay_data");
    }

    /**
     * 查询该用户最新的题目
     * @param userid
     * @return
     */
    public static String searchTest(String userid){
        String sql = "select * from test_list where userid = " + "'" + userid + "'" + " ORDER BY id DESC LIMIT 1";
        String json = JdbcTest.searchTest(sql,"pay_data");
        return json;
    }

    /**
     * ABCD和1234互换
     * @param i
     * @return
     */
    public static String option(int i){
        String option = null;
        if (i == 1){
            option = "a";
        }else if (i == 2){
            option = "b";
        }else if (i == 3){
            option = "c";
        }else if (i == 4){
            option = "d";
        }
        return option;
    }

    public static int optionId(String option){
        int optionId = 0;
        if ( option .equals("a")){
            optionId = 1;
        }else if (option.equals("b")){
            optionId = 2;
        }else if (option.equals("c")){
            optionId = 3;
        }else if (option.equals("d")){
            optionId = 4;
        }
        return optionId;
    }
}
