package com.market.pos.tool.postSearch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PostSearchService {

    public static Document postSearchService(String postid,String type){
        String baseUrl = "http://www.kuaidi100.com/query?";
        String url = baseUrl + "type=" + type + "&postid=" + postid;
        try {
            Document document = Jsoup.connect(url).get();
            return document;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
