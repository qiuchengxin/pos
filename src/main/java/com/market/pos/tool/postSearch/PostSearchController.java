package com.market.pos.tool.postSearch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.select.Elements;

public class PostSearchController {

    public static void postSearchController(String postid,String type) {
        Elements elements = PostSearchService.postSearchService(postid,type).select("body");
        String jsonString = elements.text();
        JSON json = JSON.parseObject(jsonString);
        //获取参数返回状态 是否ok
        String message = ((JSONObject) json).getString("message");
        if (message.equals("ok")) {
            JSONArray dataJSONArray = ((JSONObject) json).getJSONArray("data");
            JSONObject newData = (JSONObject) dataJSONArray.get(0);
            String ftime = newData.getString("ftime");
            String context = newData.getString("context");
            System.out.println("更新时间：" + ftime +
                    "\n订单详情：" + context);
        }else {
            System.out.println("查询异常，请确认订单号和快递公司是否匹配！");
        }
    }

    public static void main(String[] args) {
        String postid = "85731192485";
        String type = "jd";
        System.out.println("1060-6G-索泰");
        postSearchController(postid,type);

        String postid2 = "85625956173";
        System.out.println("HUAWEI-8C-4/32G");
        postSearchController(postid2,type);
    }
}
