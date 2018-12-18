package com.market.pos.tool.hideroom;

import java.util.Date;

public class test {
    public static void main(String[] args) {
        Date date = new Date() ;
        long time = date.getTime();
        System.out.println(time);

        long time_start = Long.valueOf("1544154252318");
        long time_ch = time - time_start;
        System.out.println(time_ch);

        double a = (double)time_ch / 60000;
        int b = (int)a;
        System.out.println(b);
    }
}
