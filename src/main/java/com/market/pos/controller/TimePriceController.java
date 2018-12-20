package com.market.pos.controller;

import com.market.pos.pojo.TimePrice;
import com.market.pos.service.TimePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimePriceController {

    @Autowired
    private TimePriceService timePriceService;

    @RequestMapping("/price")
    public  List<TimePrice> finAll(){
        List<TimePrice> list = timePriceService.findAll();
        return list;
    }
}
