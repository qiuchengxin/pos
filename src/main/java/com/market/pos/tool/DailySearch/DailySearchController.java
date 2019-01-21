package com.market.pos.tool.DailySearch;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DailySearchController {

    public static String dailySearch(String groupid,String qqid,String qqgroupid){

        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setQQID(qqid);
        askQQMessage.setGroupid(qqgroupid);

        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH点mm分ss秒");
        String update_time = df.format(new Date());

        DailySearchService.searchDaily(groupid);

        int i = DailySearchService.i;
        int i_today_type = DailyList.findI(i);
        String today_task = DailyList.dailyList(i_today_type);

        int j = DailySearchService.j;
        int j_today_type = DailyList.find_gg(j);
        String today_gg = DailyList.ggList(j_today_type);

        int k = DailySearchService.k;
        int k_today_zc = DailyList.find_zc(k);
        String today_zc = DailyList.zcList(k_today_zc);

        askQQMessage.setMsg("[CQ:at,qq=" + qqid + "] 现在是：" + update_time +
                            "\n今日日常如下：" +
                "\n【大战】：" + today_task +
                "\n【公共日常】：" + today_gg +
                "\n【战场】：" + today_zc);
        String ask = new Gson().toJson(askQQMessage);
        return ask;
    }
}
