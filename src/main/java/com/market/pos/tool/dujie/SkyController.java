package com.market.pos.tool.dujie;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.pk.GetGrade;

public class SkyController {

    public static String ask;

    public static void skyController(String userid,String groupid){

        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);

        GetGrade.getGrade(userid,groupid);
        int grade = GetGrade.grade;
        if (grade == 15000){
            //在sky表中生成记录并将need置为1
            GradeJudge.gradeJudge(userid,groupid);
            SkyService.updateSkyNeed(userid,groupid);
            //获取sky表的数据
            SkyService.searchSky(userid,groupid);

            int done = SkyService.done;
            System.out.println("done是：" + done);
            if (done == 1){
                askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 斗帝大人，您已经渡劫成功了！");
                ask = new Gson().toJson(askQQMessage);
            }
            if (done == 0){
                EquipJudge.equipJudge(userid,groupid);
                int equipJudge = EquipJudge.equipJudge;
                if (equipJudge == 0){
                    askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 渡劫需要背包中拥有醉月玄晶,且装备上九天逍遥套才可以" +
                            "迎战天劫，您还不满足渡劫条件 ！");
                    ask = new Gson().toJson(askQQMessage);
                }else if (equipJudge == 1){
                    System.out.println("满足了条件");
                    SkyService.searchSky(userid, groupid);
                    String one = SkyService.one;
                    String two = SkyService.two;
                    String three = SkyService.three;
                    if (one == null){
                        askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 您需要三个人的帮助，请找三个小伙伴并让他说 “我要帮助@xxx” 吧 ！");
                        ask = new Gson().toJson(askQQMessage);
                    }
                    else if (one != null && two == null){
                        askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 目前帮助您的有 ：[CQ:at,qq=" + one + "]" );
                        ask = new Gson().toJson(askQQMessage);
                    }
                    else if (one != null && two != null && three == null){
                        askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 目前帮助您的有 ：[CQ:at,qq=" + one + "] 、[CQ:at,qq=" + two + "]" );
                        ask = new Gson().toJson(askQQMessage);
                    }
                    else if (one != null && two != null && three != null){
                        askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 目前帮助您的有 ：[CQ:at,qq=" + one + "] 、[CQ:at,qq=" + two + "] 、[CQ:at,qq=" + three + "]" +
                                "\n温馨提示：以上三位中，有一位觊觎您强大的修为，想要暗中破坏，若不找出恐怕会导致您渡劫失败，请仔细调查，若有了结果，请对我说 @xx是破坏者。" );
                        ask = new Gson().toJson(askQQMessage);
                    }
                }
            }
        }if (grade < 15000){
            askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 您不满足渡劫条件" );
            ask = new Gson().toJson(askQQMessage);
        }if (grade > 15000){
            askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 您已经是斗帝了，可以接受他们的膜拜！" );
            ask = new Gson().toJson(askQQMessage);
        }
    }
}
