package com.market.pos.tool.serverOpenSearch;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AdSearch {

    public static String adSearch(String groupid){
        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setGroupid(groupid);
        String Id = AllOpenSearch.allOpenSearch();
        String url = "http://jx3.xoyo.com" + Id;
        String ask = null;
        try {
            Document document = Jsoup.connect(url).get();
            Elements element = document.select("div.detail_con");
            String text = element.text().replaceAll(" ","\n");
            askQQMessage.setMsg(text);
            ask = new Gson().toJson(askQQMessage);
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ask;
    }
}
