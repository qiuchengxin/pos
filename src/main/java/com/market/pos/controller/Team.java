package com.market.pos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/team")
public class Team {

    @RequestMapping(value = "/members",method = RequestMethod.POST)
    @ResponseBody
    public void addTeam( HttpServletRequest request){
        System.out.println("_____________________________________");
        String data11 = request.getParameter("data11").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data12 = request.getParameter("data12").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data13 = request.getParameter("data13").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data14 = request.getParameter("data14").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data15 = request.getParameter("data15").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");

        String data21 = request.getParameter("data21").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data22 = request.getParameter("data22").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data23 = request.getParameter("data23").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data24 = request.getParameter("data24").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data25 = request.getParameter("data25").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");

        String data31 = request.getParameter("data31").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data32 = request.getParameter("data32").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data33 = request.getParameter("data33").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data34 = request.getParameter("data34").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data35 = request.getParameter("data35").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");

        String data41 = request.getParameter("data41").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data42 = request.getParameter("data42").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data43 = request.getParameter("data43").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data44 = request.getParameter("data44").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data45 = request.getParameter("data45").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");

        String data51 = request.getParameter("data51").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data52 = request.getParameter("data52").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data53 = request.getParameter("data53").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data54 = request.getParameter("data54").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");
        String data55 = request.getParameter("data55").replaceAll("坦","").replaceAll("疗","").replaceAll("外","").replaceAll("内","");

        System.out.println(data11);
    }
}
