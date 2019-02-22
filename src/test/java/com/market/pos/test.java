package com.market.pos;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) throws ParseException {
        String[] packName = {"归墟玄晶","肃杀衣","肃风衣","肃和衣","肃雍衣","肃然衣","肃澹衣","昭懿裤","昭清裤","昭武裤","昭灼裤","昭苏裤","昭仰裤","昭懿戒","昭清戒","昭武戒","昭灼戒","昭苏戒","昭仰戒"};
        JSONArray json = new JSONArray();
        //如果用户第一次点开此按钮，则生成用户对应的数据
        for (int i = 0 ; i<packName.length;i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",packName[i]);
            jsonObject.put("price","0");
            jsonObject.put("peilv","0");
            jsonObject.put("num", "待刷新");
            json.add(jsonObject);
            String a = ".*";
        }
        System.out.println(json);
    }
}
