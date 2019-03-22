package com.market.pos.tool.cw;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.findTreasure.BackpackService;
import com.market.pos.tool.pk.GetQid;

/**
 * 我要锻造xxx
 */
public class MakeCwController {
    public static String ask;

    public static void makeCwController(String userid,String msg,String groupid){
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);

        GetQid.getCwName(msg);
        String cwName = GetQid.ch_msg;
        CwService.searchCw(userid,groupid);
        int is_doudi = CwService.is_doudi;

        //判定包里有无此物品
        BackpackService.searchBackPack(userid,groupid);
        String special = BackpackService.special;

        if (special == null) {
            //包里没有玄晶
            askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 您的包里并没有玄晶哦 ！");
            ask = new Gson().toJson(askQQMessage);
        }else {
            CwService.insertCw(userid, groupid);
            if (is_doudi == 0) {
                CwService.updateCwName(userid, groupid, cwName);
                CwService.delBackPackSpecial(userid, groupid);
                CwService.updateEquipSpecial(userid, groupid, cwName);
                askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 锻造成功，请查看装备栏是否无误 ！");
                ask = new Gson().toJson(askQQMessage);
            }
            if (is_doudi == 1) {
                String last_cwName = "煞·" + cwName;
                CwService.updateCwName(userid, groupid, last_cwName);
                CwService.delBackPackSpecial(userid, groupid);
                CwService.updateEquipSpecial(userid, groupid, last_cwName);
                askQQMessage.setMsg("[CQ:at,qq=" + userid + "] 锻造成功，请查看装备栏是否无误 ！");
                ask = new Gson().toJson(askQQMessage);
            }
        }
    }
}
