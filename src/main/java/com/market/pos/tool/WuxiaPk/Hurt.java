package com.market.pos.tool.WuxiaPk;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.Equip.EquipService;
import com.market.pos.tool.pk.GetGrade;
import com.market.pos.tool.pk.GetQid;

public class Hurt {

    public static int last_hurt;
    public static String ask;
    //data:招式名称
    public static void hurt(String qqid,String data,String groupid,String msg){

        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);
        askQQMessage.setQQID(qqid);

        EquipService.equipSelect(qqid);
        String equip_book = EquipService.book;

        //获取被挑战者id
        GetQid.getQid(msg);
        String be_qqid = GetQid.ch_qqid;

        //判断当前对象usertwo是否可挑战
        GetGrade.getGrade(be_qqid);
        int usertwo_grade = GetGrade.grade;

        //获取对方防御信息
        BeHurt.beHurt(be_qqid);
        double behurt = BeHurt.behurt;

        EquipService.equipSelect(qqid);
        int pkdaily = EquipService.finddaily;

        if (pkdaily == 0) {
            System.out.println("进来了");
            if (usertwo_grade < 150) {
                askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 他还是个弟弟，不能对其施展招式 ！ ");
                ask = new Gson().toJson(askQQMessage);
            } else {
                if (data.equals(equip_book) == false) {
                    //没有装备此秘籍
                    askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 您并没有装备此秘籍 ！ ");
                    ask = new Gson().toJson(askQQMessage);
                } else {
                    if (data.equals("六脉神剑") == true) {
                        int shaoshang = (int) (Math.random() * 6 + 1);
                        int shangyang = (int) (Math.random() * 6 + 1);
                        int zhongchong = (int) (Math.random() * 6 + 1);
                        int shaochong = (int) (Math.random() * 6 + 1);
                        System.out.println(shaoshang);

                        int hurtInt = shaoshang * shangyang * zhongchong * shaochong;
                            int last_hurt = (int) (hurtInt * (1-behurt));
                            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 你成功施展了六脉神剑 ！" +
                                    "\n六脉神剑伤害：少商剑（" + shaoshang + "）" + "、"
                                    + "商阳剑（" + shangyang + "）" + "、"
                                    + "中冲剑（" + zhongchong + "）" + "、"
                                    + "少冲剑（" + zhongchong + "）" + "、"
                                    + "\n共计伤害：" + last_hurt + "点！");
                            ask = new Gson().toJson(askQQMessage);
                            int last_grade = usertwo_grade - last_hurt;
                            GetGrade.update_behurt_user(be_qqid, last_grade);
                            GetGrade.update_wuxiapk_daily(qqid);
                    }

                    if (data.equals("化功大法") == true) {
                        last_hurt = (int) ((Math.random() * 100 + 200)*(1-behurt));
                            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 你成功施展了化功大法 ！" +
                                    "\n成功散去对方" + last_hurt + "点修为！");
                            ask = new Gson().toJson(askQQMessage);
                            int last_grade = usertwo_grade - last_hurt;
                            GetGrade.update_behurt_user(be_qqid, last_grade);
                            GetGrade.update_wuxiapk_daily(qqid);
                    }

                    if (data.equals("六脉神剑") == false && data.equals("化功大法") == false){
                        askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 该招式还在设计之中，侠士过两天再来把（目前江湖中只出现了六脉神剑和化功大法） ！");
                        ask = new Gson().toJson(askQQMessage);
                    }
                }
            }
        }
        if (pkdaily == 1){
            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 招式尚在调息之中，少侠明日再来 ！");
            ask = new Gson().toJson(askQQMessage);
        }
    }
}
