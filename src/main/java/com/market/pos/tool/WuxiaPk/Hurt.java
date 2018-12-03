package com.market.pos.tool.WuxiaPk;

import com.market.pos.tool.Equip.EquipService;

public class Hurt {

    public static int last_hurt;
    //data:招式名称
    public static void hurt(String qqid,String be_qqid,String data){
        EquipService.equipSelect(qqid);
        String equip_book = EquipService.book;

        //获取对方防御信息
        BeHurt.beHurt(be_qqid);
        double behurt = BeHurt.behurt;
        if (data.equals(equip_book) == false){
            //没有装备此秘籍
        }else {
            if (data.equals("六脉神剑") == true){
                int shaoshang = (int)Math.random()*6 + 1;
                int shangyang = (int)Math.random()*6 + 1;
                int zhongchong = (int)Math.random()*6 + 1;
                int shaochong = (int)Math.random()*6 + 1;

                double hurtInt = shaoshang * shangyang * zhongchong * shaochong;
                if (behurt == 0){
                    last_hurt = (int)hurtInt;
                }else {
                    last_hurt = (int) (hurtInt * behurt);
                }
            }
        }
    }
}
