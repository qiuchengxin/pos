package com.market.pos.tool.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetEncoding {

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GB2312
                String s = encode;
                return s; //是的话，返回“GB2312“，以下代码同理
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是ISO-8859-1
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是UTF-8
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GBK
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }

    /**
     * 秒转换为指定格式的日期
     * @param second
     * @param patten
     * @return
     */
    public static String secondToDate(long second,String patten) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(second * 1000);//转换为毫秒
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(patten);
        String dateString = format.format(date);
        return dateString;
    }
}
