package com.market.pos.tool.wujiaHeZi;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetHeziList {
    public static String getHeziList(String userid,String msg) {
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("106");
        askQQMessage.setQQID(userid);
        String ask = null;

        String msg_start = "物价查询";
        Pattern pattern = Pattern.compile(msg_start);
        Matcher matcher = pattern.matcher(msg);
        String good = matcher.replaceAll("").trim();
        System.out.println(good);
        String keyword = null;
        try {
            //将商品名称转换为gb2312编码
            keyword = URLEncoder.encode(good,"gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://s.5173.com/jx3-0-0-0-0-huabyt-0-0-0-a-a-a-a-a-0-0-0-0.shtml?keyword=" + keyword;
        System.out.println(url);
        try {
            Document document = Jsoup.connect(url).get();
            String list = document.select("li.pr").text().replaceAll(" ",",");
            askQQMessage.setMsg("由5173数据查询得到价格列表如下(5173价格偏高，娱乐就好)：" +
                    "\n" + list);
            ask = new Gson().toJson(askQQMessage);

            String server = document.select("a[href][title]").text().replaceAll(".*关闭  外观","").
                    replaceAll("安心买  外观","").replaceAll("安心买","")
                    .replaceAll("剑侠情缘Ⅲ 金山 ","")
                    .replaceAll("双线二区","")
                    .replaceAll("双线一区","")
                    .replaceAll("电信一区","")
                    .replaceAll("电信五区","")
                    .replaceAll("电信八区","")
                    .replaceAll("  ",",")
                    .replaceAll(" ","");

            System.out.println(list);
            System.out.println(server);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ask;
    }

    public static void main(String[] args) {
        getHeziList("1218129325","鸡盒子");
    }
}

