package com.market.pos.controller;

import com.market.pos.pojo.TeamList;
import com.market.pos.pojo.TeamMembers;
import com.market.pos.service.ITeamMembersService;
import com.market.pos.service.JPAService.TeamListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TeamMembersController {

    @Autowired
    private ITeamMembersService iTeamMembersService;

    @Autowired
    private TeamListRepository teamListRepository;

    @GetMapping(value = "/join/{id}")
    public String addTeamMember(@PathVariable("id") long id, Model model, HttpServletRequest request){
        TeamList teamlist =  teamListRepository.findById(id);
        String tID = teamlist.getTId();

        TeamMembers teamMembers = new TeamMembers();
        String username = request.getParameter("username");
        String usertype = request.getParameter("usertype");
        String is_tibu = request.getParameter("is_tibu");
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");

        String data_usertype = iTeamMembersService.findUserType(userid,tID);
        if (data_usertype == null) {
            teamMembers.setTId(tID);
            teamMembers.setUserid(userid);
            teamMembers.setUsername(username);
            teamMembers.setUsertype(usertype);
            teamMembers.setIsTibu(is_tibu);
            iTeamMembersService.addTeamMember(teamMembers);

            String back = "报名成功！您报名的职业是： " + usertype;
            model.addAttribute("back", back);
        }else if (data_usertype != null){
            String back = "您已经报名过了，不可以重复报名！";
            model.addAttribute("back",back);
        }
        return "baomingback";
    }
}
