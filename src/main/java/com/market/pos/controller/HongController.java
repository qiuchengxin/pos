package com.market.pos.controller;

import com.market.pos.service.HongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/hong")
public class HongController {

    @Autowired
    private HongService hongService;

    @RequestMapping("/honglist")
    public String honglist(){
        return "honglist";
    }

    @GetMapping("/type={type}")
    public String getHongByUserIdAndType(HttpServletRequest request, HttpServletResponse response, Model model
                        , @PathVariable("type") String type) throws IOException {
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");
        if (userid == null){
            response.sendRedirect("/Members/login");
        }
        String list = hongService.getHongByUserIdAndType(userid,type);
//        String hong = list.replaceAll("/cast","\n/cast");
        model.addAttribute("hong",list);
        model.addAttribute("type",type);
        return "hong";
    }
}
