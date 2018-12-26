package com.market.pos.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/team")
public class Team {

    @RequestMapping(value = "/members",method = RequestMethod.POST)
    public void addTeam( @RequestParam(value = "url[]",required = false,defaultValue = "") String[] url,
                         HttpServletResponse response){
        System.out.println(url);
    }
}
