package com.market.pos.tool.timeTask;

import com.market.pos.tool.goldPriceSearch.GoldPriceSearch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时任务
 */
public class TimeTask {
    public static void timeTask() {
        Timer tim = new Timer();
        TimerTask tTask = new TimerTask() {
            @Override
            public void run() {
                String money = GoldPriceSearch.htmlFiter("https://www.dd373.com/s/8v8pc2-hswdmk-jvtg2x-0-0-0-cwmaee-0-0-0-0-0-0-0-0.html");
                //获取更新时间 update_time
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String update_time = df.format(new Date());
            }
        };
        tim.schedule(tTask,0,30*60*1000);
    }
}
