package com.market.pos.controller;

import com.market.pos.service.IBackpackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BackpackController {

    @Autowired
    private IBackpackService iBackpackService;

   @RequestMapping("/money")
    public String searchMoney(){
       String money = iBackpackService.selectMoney(1218);
       return money;
   }
}
