package com.market.pos.tool.pk;import java.util.regex.Matcher;import java.util.regex.Pattern;public class GetQid {    public static String ch_qqid;    /**     * 获取被艾特人的QQid     * @param msg     */    public static void getQid(String msg) {        String reg = "[^0-9]";        Pattern pattern = Pattern.compile(reg);        Matcher matcher = pattern.matcher(msg);        ch_qqid = matcher.replaceAll("").trim();    }}