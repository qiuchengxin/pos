package com.market.pos.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.market.pos.pojo.Qa;
import com.market.pos.service.QAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/QA")
public class QAController {

    @Autowired
    private QAService qaService;

    @RequestMapping("/list")
    public String qaList(Model model){
        List<Qa> list = qaService.getAllQA();
        String jsonStringAll = JSON.toJSONString(list);
        JSONArray jsonArrayAll = JSONArray.parseArray(jsonStringAll);
        model.addAttribute("json",jsonArrayAll);
        return "QA";
    }
}
