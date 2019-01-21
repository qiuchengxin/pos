package com.market.pos.tool.DailySearch;

import com.market.pos.tool.connect.JdbcDaily;

public class DailySearchService {

    public static int i;
    public static int j;
    public static int k;

    public static void searchDaily(String groupid){
        String sql = "select * from daily where id = '1'";
        JdbcDaily.searchDaily(sql,groupid);
        i = JdbcDaily.i;
        j = JdbcDaily.j;
        k = JdbcDaily.k;
    }
}
