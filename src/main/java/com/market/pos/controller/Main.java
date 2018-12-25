package com.market.pos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/main")
public class Main {

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");
        if (userid == null){
            response.sendRedirect("/Members/login");
        }
        model.addAttribute("userid",userid);
        return "index";
    }

    @RequestMapping("/403")
    public String none(){
        return "403";
    }
}
