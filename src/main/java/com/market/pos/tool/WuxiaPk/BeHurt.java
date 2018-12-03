package com.market.pos.tool.WuxiaPk;

import com.market.pos.tool.Equip.EquipService;

public class BeHurt {
    public static double behurt;

    public static void beHurt(String be_qqid){
        EquipService.equipSelect(be_qqid);
        String be_equipment = EquipService.equipment;

        if (be_equipment == null){
            behurt = 0;
        }else {
            int be_num = (int)(Math.random()*100);
            if (be_equipment.equals("儒风套")){
                if (be_num <= 70){
                    behurt = 0;
                }else {
                    behurt = 0.1;
                }
            }

            if (be_equipment.equals("文王套")){
                if (be_num <= 70){
                    behurt = 0;
                }else {
                    behurt = 0.2;
                }
            }

            if (be_equipment.equals("雪河套")){
                if (be_num <= 70){
                    behurt = 0;
                }else {
                    behurt = 0.3;
                }
            }

            if (be_equipment.equals("燕云套")){
                if (be_num <= 70){
                    behurt = 0;
                }else {
                    behurt = 0.4;
                }
            }

            if (be_equipment.equals("驰冥套")){
                if (be_num <= 70){
                    behurt = 0;
                }else {
                    behurt = 0.5;
                }
            }

            if (be_equipment.equals("未烬套")){
                if (be_num <= 70){
                    behurt = 0;
                }else {
                    behurt = 0.6;
                }
            }

            if (be_equipment.equals("九天逍遥套")){
                if (be_num <= 70){
                    behurt = 0;
                }else {
                    behurt = 0.7;
                }
            }
        }
    }
}
