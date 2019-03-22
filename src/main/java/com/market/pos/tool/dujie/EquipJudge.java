package com.market.pos.tool.dujie;

import com.market.pos.tool.Equip.EquipService;
import com.market.pos.tool.findTreasure.BackpackService;

public class EquipJudge {

    public static int equipJudge;

    public static void equipJudge(String userid,String groupid){
        EquipService.equipSelect(userid,groupid);
        String equipment = EquipService.equipment;

        BackpackService.searchBackPack(userid,groupid);
        String special = BackpackService.special;

        if (equipment == null || special == null){
            equipJudge = 0;
        }else {
            if (equipment.matches(".*九天逍遥套.*") && special.matches(".*醉月玄晶.*")) {
                equipJudge = 1;
            } else {
                equipJudge = 0;
            }
        }
    }
}
