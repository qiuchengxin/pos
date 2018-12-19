package com.market.pos.tool.serverOpenSearch;

import com.market.pos.tool.pk.GetQid;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AllOpenSearch {

    public static String allOpenSearch(){
        String url = "http://jx3.xoyo.com/announce/";
        String linkHref = null;
        try {
            Document document = Jsoup.connect(url).get();
            Elements allServers = document.select("a[title][href]");
            Document document1 = Jsoup.parse(String.valueOf(allServers));

            Element link = document1.select("a").first();
            linkHref = link.attr("href");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linkHref;
    }
}
