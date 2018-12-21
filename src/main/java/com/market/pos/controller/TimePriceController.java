package com.market.pos.controller;

import com.market.pos.pojo.TimePrice;
import com.market.pos.service.TimePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


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
}
