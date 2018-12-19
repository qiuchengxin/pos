package com.market.pos.tool.WuxiaPk;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.Equip.EquipService;
import com.market.pos.tool.cw.ResetDaily;
import com.market.pos.tool.pk.GetGrade;
import com.market.pos.tool.pk.GetQid;

public class Hurt {

    public static int last_hurt;
    public static String ask;
    public static String cwAsk;
    public static int cd;
    //data:招式名称
    public static void hurt(String qqid,String groupid,String msg){

        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);
        askQQMessage.setQQID(qqid);

        EquipService.equipSelect(qqid,groupid);
        String equip_book = EquipService.book;

        //获取被挑战者id
        GetQid.getQid(msg);
        String be_qqid = GetQid.ch_qqid;

        //判断当前对象usertwo是否可挑战
        GetGrade.getGrade(be_qqid,groupid);
        int usertwo_grade = GetGrade.grade;

        //获取挑战者的修为
        GetGrade.getGrade(qqid,groupid);
        int user_grade = GetGrade.grade;

        //获取对方防御信息
        BeHurt.beHurt(be_qqid,groupid);
        double behurt = BeHurt.behurt;

        EquipService.equipSelect(qqid,groupid);
        int pkdaily = EquipService.finddaily;

        if (pkdaily < 1) {
            System.out.println("进来了");
            ResetDaily.resetDaily(qqid,groupid);
            int cwTeXiao = ResetDaily.cwTeXiao;
            if (cwTeXiao == 1){
                cwAsk = ResetDaily.ask;
            }else if (cwTeXiao == 0){
                cwAsk = "nocd";
            }
            if (usertwo_grade < 150) {
                askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 他还是个弟弟，不能对其施展招式 ！ ");
                ask = new Gson().toJson(askQQMessage);
            } else {
                if (msg.matches(".*"+equip_book+".*")) {
                    if (msg.matches(".*六脉神剑.*")) {
                        int shaoshang = (int) (Math.random() * 6 + 1);
                        int shangyang = (int) (Math.random() * 6 + 1);
                        int zhongchong = (int) (Math.random() * 6 + 1);
                        int shaochong = (int) (Math.random() * 6 + 1);
                        int guanchong = (int) (Math.random() * 6 + 1);
                        int shaoze = (int) (Math.random() * 6 + 1);

                        System.out.println(shaoshang);

                        int hurtInt = shaoshang * shangyang * zhongchong * shaochong * guanchong * shaoze;
                        int last_hurt = (int) (hurtInt * (1-behurt));
                        askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 你成功施展了六脉神剑 ！" +
                                "\n六脉神剑伤害：少商剑（" + shaoshang + "）" + "、"
                                + "商阳剑（" + shangyang + "）" + "、"
                                + "中冲剑（" + zhongchong + "）" + "、"
                                + "少冲剑（" + zhongchong + "）" + "、"
                                + "关冲剑（" + guanchong + "）" + "、"
                                + "少泽剑（" + shaoze + "）" + "、"
                                + "\n共计伤害：" + last_hurt + "点！");
                        ask = new Gson().toJson(askQQMessage);
                        int last_grade = usertwo_grade - last_hurt;
                        GetGrade.update_behurt_user(be_qqid, last_grade,groupid);
                        GetGrade.update_wuxiapk_daily(qqid,groupid);
                    }

                    if (msg.matches(".*化功大法.*")) {
                        last_hurt = (int) ((Math.random() * 100 + 200)*(1-behurt));
                        askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 你成功施展了化功大法 ！" +
                                "\n成功散去对方" + last_hurt + "点修为！");
                        ask = new Gson().toJson(askQQMessage);
                        int last_grade = usertwo_grade - last_hurt;
                        GetGrade.update_behurt_user(be_qqid, last_grade,groupid);
                        GetGrade.update_wuxiapk_daily(qqid,groupid);
                    }

                    if (msg.matches(".*佛怒火莲.*")){
                        int panding = (int)(Math.random()*100);
                        if (panding <= 20){
                            int beuser_last_grade = (int)(usertwo_grade * 0.5);
                            int user_last_grade = (int)(user_grade * 0.5);
                            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 阁下倾尽全力，成功释放了佛怒火莲，但是因为根基尚浅受到反噬，双方修为都受到了重创 ！" +
                                    "\n双方修为减半 ！");
                            ask = new Gson().toJson(askQQMessage);
                            GetGrade.update_behurt_user(qqid,user_last_grade,groupid);
                            GetGrade.update_behurt_user(be_qqid,beuser_last_grade,groupid);
                            GetGrade.update_wuxiapk_daily(qqid,groupid);
                        }
                        if (panding > 20 && panding <=30){
                            int beuser_last_grade = (int)(usertwo_grade * 0.7);
                            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 恭喜你成功释放了佛怒火莲，对方受到了重创 ！" +
                                    "\n对方修为减去三分之一 ！");
                            ask = new Gson().toJson(askQQMessage);
                            GetGrade.update_behurt_user(be_qqid,beuser_last_grade,groupid);
                            GetGrade.update_wuxiapk_daily(qqid,groupid);
                        }
                        if (panding > 30){
                            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 释放佛怒火莲失败，是不是钱充少了 ！" +
                                    "\n明天再来试试把 ！");
                            ask = new Gson().toJson(askQQMessage);
                        }
                    }

                    if (msg.matches(".*吸功大法*")){
                        int hurt = (int) (Math.random() * 100 + 300);
                        int beuser_last_grade = usertwo_grade - hurt;
                        int user_last_grade = user_grade + hurt;

                        askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 恭喜你成功释放了吸功大法 ！" +
                                "\n吸收了对方 "+hurt+"点修为 ！");
                        ask = new Gson().toJson(askQQMessage);
                        GetGrade.update_behurt_user(qqid,user_last_grade,groupid);
                        GetGrade.update_behurt_user(be_qqid,beuser_last_grade,groupid);
                        GetGrade.update_wuxiapk_daily(qqid,groupid);
                    }

                    if (msg.matches(".*移花接木.*")){
                       int zhongjian_grade = usertwo_grade;
                       int gongji = zhongjian_grade;
                       int beigongji = user_grade;
                       int xiuweicha = gongji - beigongji;
                       if (xiuweicha <= 0){
                           askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 你获得了弟弟修为，反被揍了一顿,掉了 "+(int)(500*(1-behurt))+" 点修为 ！");
                           ask = new Gson().toJson(askQQMessage);
                           int user_last_grade = user_grade - (int)(500*(1-behurt));
                           GetGrade.update_behurt_user(qqid,user_last_grade,groupid);
                           GetGrade.update_wuxiapk_daily(qqid,groupid);
                       }else {
                           askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 成功获得对方修为并把对方打哭了 ！" +
                                   "\n对方掉了 "+(int)(500*(1-behurt))+" 点修为！");
                           ask = new Gson().toJson(askQQMessage);
                           int beuser_last_grade = usertwo_grade - (int)(500*(1-behurt));
                           GetGrade.update_behurt_user(be_qqid,beuser_last_grade,groupid);
                           GetGrade.update_wuxiapk_daily(qqid,groupid);
                       }
                    }

                    if (msg.matches(".*焰分噬浪尺.*")){
                        int panding = (int)(Math.random()*100);
                        if (panding <= 20){
                            int hurt = (int)(usertwo_grade * 0.5 );
                            int beuser_grade = usertwo_grade - (int)(hurt*(1-behurt));

                            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 你大喊一声焰分噬浪尺 ！" +
                                    "\n对方roll点结果为奇数，被你成功命中，修为减半,减去了 "+(int)(hurt*(1-behurt))+" 点修为！");
                            ask = new Gson().toJson(askQQMessage);
                            GetGrade.update_behurt_user(be_qqid,beuser_grade,groupid);
                            GetGrade.update_wuxiapk_daily(qqid,groupid);
                        }else {
                            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 你大喊一声焰分噬浪尺 ！" +
                                    "\n对方roll点结果为偶数，身法飘逸的逃开了 ！");
                            ask = new Gson().toJson(askQQMessage);
                            GetGrade.update_wuxiapk_daily(qqid,groupid);
                        }
                    }

                    if (msg.matches(".*八极崩.*")){
                        int panding = (int)(Math.random()*100);
                        if (panding <= 25){
                            int beuser_grade = usertwo_grade - (int)(8*(1-behurt));
                            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 八极崩 ！！！" +
                                    "\n你嚷嚷的倒是挺凶，结果八极崩只打出了一段伤害，对方被打掉了 "+(int)(8*(1-behurt))+" 点修为。");
                            ask = new Gson().toJson(askQQMessage);
                            GetGrade.update_behurt_user(be_qqid,beuser_grade,groupid);
                            GetGrade.update_wuxiapk_daily(qqid,groupid);
                        }
                        if (panding > 25 && panding <= 50){
                            int beuser_grade = usertwo_grade - (int)(64*(1-behurt));
                            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 八极崩 ！！！" +
                                    "\n你倾尽船力释放出八极崩，结果只打出了二段伤害，对方被打掉了 "+(int)(64*(1-behurt))+" 点修为。");
                            ask = new Gson().toJson(askQQMessage);
                            GetGrade.update_behurt_user(be_qqid,beuser_grade,groupid);
                            GetGrade.update_wuxiapk_daily(qqid,groupid);
                        }
                        if (panding > 50 && panding <=90){
                            int beuser_grade = usertwo_grade - (int)(512*(1-behurt));
                            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 八极崩 ！！！" +
                                    "\n你轻描淡写释放了八极崩，打出了三段伤害，对方被打掉了 "+(int)(512*(1-behurt))+" 点修为。");
                            ask = new Gson().toJson(askQQMessage);
                            GetGrade.update_behurt_user(be_qqid,beuser_grade,groupid);
                            GetGrade.update_wuxiapk_daily(qqid,groupid);
                        }
                        if (panding > 90){
                            int beuser_grade = usertwo_grade - (int)(4096*(1-behurt));
                            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 八极崩 ！！！" +
                                    "\n阁下武学造诣简直让人惊叹，竟然将八极崩练到了第四重境界！，对方被打掉了 "+(int)(4096*(1-behurt))+" 点修为 ！");
                            ask = new Gson().toJson(askQQMessage);
                            GetGrade.update_behurt_user(be_qqid,beuser_grade,groupid);
                            GetGrade.update_wuxiapk_daily(qqid,groupid);
                        }
                    }

                    if (msg.matches(".*降龙十八掌.*")){
                        askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 降龙十八掌是传说中的武学，你还无法参透！洗洗睡吧 ！");
                        ask = new Gson().toJson(askQQMessage);
                    }
                } else {
                    //没有装备此秘籍
                    askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 您并没有装备此秘籍 ！ ");
                    ask = new Gson().toJson(askQQMessage);
                }
            }
        }
        if (pkdaily >= 1){
            askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 招式尚在调息之中，少侠明日再来 ！");
            ask = new Gson().toJson(askQQMessage);
        }
    }
}
