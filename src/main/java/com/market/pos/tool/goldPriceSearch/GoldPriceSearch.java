package com.market.pos.tool.goldPriceSearch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class GoldPriceSearch {

    public static String htmlFiter(String url)  {
        Document doc = null;
        String money = null;
        try {
            doc = Jsoup.connect(url).get();
            Elements Allmoney = doc.select("p");
            money = Allmoney.get(5).text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return money;
    }

}
