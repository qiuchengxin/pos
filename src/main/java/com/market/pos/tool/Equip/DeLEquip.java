package com.market.pos.tool.Equip;

public class DeLEquip {

    public static void delEquip(String qqid , String data_type , String data,String groupid){
        EquipService.equipSelect(qqid,groupid);
        String now_book = EquipService.book;
        String now_equipment = EquipService.equipment;
        if (data_type.equals("equipment")){
            if (now_equipment != null){
                if (now_equipment.equals(data)){
                    EquipService.equipUpdate(qqid,"equipment",null,groupid);
                }
            }
        }
        if (data_type.equals("book")){
            if (now_book != null){
                if (now_book.equals(data)){
                    EquipService.equipUpdate(qqid,"book",null,groupid);
                }
            }
        }
    }
}
