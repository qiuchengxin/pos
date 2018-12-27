package com.market.pos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/view")
public class ViewController {

    @RequestMapping("/make")
    public String teamMake(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");
        if (userid != "admin"){
            response.sendRedirect("/main/403");
        }
        return "teammake";
    }

    @RequestMapping("/made")
    public String teamMade(){ return "teammade"; }
}
