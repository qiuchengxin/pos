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

        teamMembers.setTId(tID);
        teamMembers.setUserid(userid);
        teamMembers.setUsername(username);
        teamMembers.setUsertype(usertype);
        teamMembers.setIsTibu(is_tibu);
        iTeamMembersService.addTeamMember(teamMembers);
        model.addAttribute("return","报名成功！");
        return "teammade";
    }
}
