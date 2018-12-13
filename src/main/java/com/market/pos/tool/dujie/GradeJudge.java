package com.market.pos.tool.dujie;

import com.market.pos.tool.pk.GetGrade;

public class GradeJudge {

    public static void gradeJudge(String userid,String groupid){
        GetGrade.getGrade(userid,groupid);
        int grade = GetGrade.grade;
        if (grade >= 15000 && grade < 20000){
            //小天劫

        }
        if (grade >= 20000){
            //斗帝劫
        }
    }
}
