package com.market.pos.controller;

import com.market.pos.pojo.TimePrice;
import com.market.pos.service.TimePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TimePriceController {

    @Autowired
    private TimePriceService timePriceService;

    @RequestMapping("/list")
    public String priceTable(Model model){
        List<TimePrice> list = timePriceService.findAll();
        model.addAttribute("list",list);
        return "list";
    }

    @RequestMapping("/echarts")
    public String echart(Model model){
        List<TimePrice> list = timePriceService.findAll();
        List<TimePrice> listAsc = timePriceService.findAsc();
        List<Double> money = new ArrayList<>();
        List<Integer> time = new ArrayList<>();

        for (int i=0;i<listAsc.size();i++){
            String one = listAsc.get(i).getMoney();
            System.out.println(one);
            String two = one.replaceAll("1元=","").replaceAll("金","");
            money.add(Double.valueOf(two));
            time.add(i);
        }

        model.addAttribute("list",list);
        model.addAttribute("money",money);
        model.addAttribute("time",time);
        return "echarts";
    }
}
