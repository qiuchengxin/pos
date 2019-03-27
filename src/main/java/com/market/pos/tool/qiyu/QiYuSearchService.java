package com.market.pos.tool.qiyu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class QiYuSearchService {

    public static Document qiYuSearch(long id){
        String url = "http://www.yayaquanzhidao.com/?ID=" + id;
        try {
            Document document = Jsoup.connect(url).get();
            return document;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
