package com.market.pos.tool.timeTask;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.market.pos.pojo.TimePrice;

import java.util.List;

public class GetJson {

    public  JSONArray getJson(List<TimePrice> list){
        JSONArray jsonArray = new JSONArray();
        for (int i = 0;i <= list.size();i++){
            String money = list.get(i).getMoney();
            String time = list.get(i).getTime();
            String jsonString = "{\"money\":\""+money+"\",\"time\":\""+time+"\"}";
            JSONObject jsonObject = JSON.parseObject(jsonString);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
