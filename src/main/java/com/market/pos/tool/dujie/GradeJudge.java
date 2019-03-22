package com.market.pos.tool.dujie;

public class GradeJudge {

    public static void gradeJudge(String userid,String groupid){
        //将grade大于20000的用户数据插入sky表并将need置为1
        SkyService.searchSky(userid,groupid);
        String reslut_userid = SkyService.result_userid;
        if (reslut_userid == null){
            SkyService.insertSky(userid,groupid);
        }
    }
}
