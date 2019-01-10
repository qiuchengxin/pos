package com.market.pos.controller;

import com.market.pos.pojo.TeamList;
import com.market.pos.service.ITeamTreeService;
import com.market.pos.service.TeamListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/view")
public class ViewController {

    @Autowired
    private TeamListService teamListService;

    @RequestMapping("/make")
    public String teamMake(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");
        if (!userid.equals("admin")){
            response.sendRedirect("/main/403");
        }
        return "teammake";
    }

    @RequestMapping("/made")
    public String teamMade(){
        return "teammade";
    }

    @RequestMapping("/teamList")
    public String teamList(Model model){
        List<TeamList> list = teamListService.selectAllTeamList();
        model.addAttribute("list",list);
        return "teamlist";
    }

    @RequestMapping("/suc")
    public String suc(Model model){
        model.addAttribute("return","招募发布成功！");
        return "suc";
    }

    @RequestMapping(value = "/info",method = RequestMethod.POST)
    public String GetTeamInfo(@PathVariable("id") Integer id){
        System.out.println(id);
        return "teammade";
    }

    @RequestMapping("/zhuye")
    public String zhuye(Model model){
        return "zhuye";
    }
}
