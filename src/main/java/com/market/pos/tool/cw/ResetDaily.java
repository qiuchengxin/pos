package com.market.pos.tool.cw;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.Equip.EquipService;

public class ResetDaily {
    public static String ask;
    public static int cwTeXiao;

    public static void resetDaily(String userid,String groupid) {

        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);

        CwService.searchCw(userid, groupid);
        String result_userid = CwService.result_userid;
        String cw_name = CwService.cw_name;
        int daily = CwService.daily;
        int cd = CwService.cd;

        //判定要拥有cw
        if (result_userid != null && cw_name != null) {
            int cw_num = (int) (Math.random() * 100);
            //触发cw特效
            if (cw_num >= 30) {
                if (cd == 0) {
                    //还有cd
                    //判定有特效
                    cwTeXiao = 1;
                    askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 触发橙武特效【" + cw_name + "】,还可使用两次技能！");
                    ask = new Gson().toJson(askQQMessage);
                    EquipService.equipUpdate(userid, "finddaily", "-2", groupid);
                }
            }
        }else {
            cwTeXiao = 0;
        }
    }
}
