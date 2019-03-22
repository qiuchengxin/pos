package com.market.pos.tool.DailySearch;

public class DailyList {

    /**
     * 大战
     * @param today_type
     * @return
     */
    public static String dailyList(int today_type){
        String today_task = null;
        if (today_type == 1){
            today_task = "英雄大衍盘丝洞";
        }else if (today_type == 2){
            today_task = "英雄九辩馆";
        }else if (today_type == 3){
            today_task = "英雄镜泊湖";
        }else if (today_type == 4){
            today_task = "英雄迷渊岛";
        }else if (today_type == 5){
            today_task = "英雄泥兰洞天";
        }
        return today_task;
    }

    /**
     * 公共日常
     * @param today_type
     * @return
     */
    public static String ggList(int today_type){
        String today_gg = null;
        if (today_type == 1){
            today_gg = "龙泉府·雪国冬猎";
        }else if (today_type == 2){
            today_gg = "洞天福地·守卫泥兰";
        }else if (today_type == 3){
            today_gg = "经首道源·越海珠贝";
        }else if (today_type == 4){
            today_gg = "侠客岛·雾岛寻丹";
        }
        return today_gg;
    }

    public static String zcList(int today_type){
        String today_zc = null;
        if (today_type == 1){
            today_zc = "三国古战场";
        }else if (today_type == 2){
            today_zc = "浮香丘";
        }else if (today_type == 3){
            today_zc = "丝绸之路";
        }else if (today_type == 4){
            today_zc = "神农烟";
        }else if (today_type == 5){
            today_zc = "九宫棋谷";
        }else if (today_type == 6){
            today_zc = "云湖战场";
        }
        return today_zc;
    }

    public static int findI(int i){
        Integer[] list = {1,2,1,2,3,4,3,4,5,1,5};
        int today_type = list[i];
        return today_type;
    }

    public static int find_gg(int j){
        Integer[] list = {1,2,3,4};
        int today_type = list[j];
        return today_type;
    }

    public static int find_zc(int k){
        Integer[] list = {1,2,3,4,5,6};
        int today_type = list[k];
        return today_type;
    }
}
