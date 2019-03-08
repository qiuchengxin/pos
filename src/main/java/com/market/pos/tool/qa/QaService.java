package com.market.pos.tool.qa;

import com.market.pos.tool.connect.JdbcQA;

public class QaService {

    public static String qaSearch(String question){
        String sql = "SELECT answer from qa where question = " + "'" + question + "'";
        String answer = JdbcQA.searchQA(sql,"pay_data");
        return answer;
    }
}
