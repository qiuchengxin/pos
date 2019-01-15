package com.market.pos.controller;

import com.market.pos.mapper.ITeamListMapper;
import com.market.pos.pojo.TeamList;
import com.market.pos.pojo.TeamTree;
import com.market.pos.service.ITeamMembersService;
import com.market.pos.service.ITeamTreeService;
import com.market.pos.service.TeamListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/team")
public class TeamTreeController {

    @Autowired
    private ITeamTreeService iTeamTreeService;

    @Autowired
    private TeamListService teamListService;

    @Autowired
    private ITeamMembersService iTeamMembersService;

    @RequestMapping("/members")
    public void addTeam(HttpServletRequest request,Model model){

        TeamList teamList = new TeamList();
        //form传来的
        String t_id = request.getParameter("t_id");
        String t_name = request.getParameter("t_name");
        String t_type = request.getParameter("t_type");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String liuyan = request.getParameter("liuyan");
        if (liuyan.equals("")){
            teamList.setLiuyan("他并未发布留言哦！");
        }else {
            teamList.setLiuyan(liuyan);
        }
        String t_time = date +time;
        teamList.setTId(t_id);
        teamList.setTName(t_name);
        teamList.setTTime(t_time);
        teamList.setTType(t_type);
        teamListService.insertTeamList(teamList);
    }

    @RequestMapping("/tree")
    public void addTree(HttpServletRequest request,Model model){
        TeamTree teamTree = new TeamTree();
        //ajax传来的
        String t_id = request.getParameter("t_id");
        try {
            String data11 = request.getParameter("data11").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data12 = request.getParameter("data12").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data13 = request.getParameter("data13").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data14 = request.getParameter("data14").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data15 = request.getParameter("data15").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data21 = request.getParameter("data21").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data22 = request.getParameter("data22").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data23 = request.getParameter("data23").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data24 = request.getParameter("data24").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data25 = request.getParameter("data25").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data31 = request.getParameter("data31").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data32 = request.getParameter("data32").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data33 = request.getParameter("data33").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data34 = request.getParameter("data34").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data35 = request.getParameter("data35").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data41 = request.getParameter("data41").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data42 = request.getParameter("data42").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data43 = request.getParameter("data43").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data44 = request.getParameter("data44").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data45 = request.getParameter("data45").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data51 = request.getParameter("data51").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data52 = request.getParameter("data52").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data53 = request.getParameter("data53").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data54 = request.getParameter("data54").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");
            String data55 = request.getParameter("data55").replaceAll("坦", "").replaceAll("疗", "").replaceAll("外", "").replaceAll("内", "");

            teamTree.setTId(t_id);
            teamTree.setT11(data11);
            teamTree.setT12(data12);
            teamTree.setT13(data13);
            teamTree.setT14(data14);
            teamTree.setT15(data15);
            teamTree.setT21(data21);
            teamTree.setT22(data22);
            teamTree.setT23(data23);
            teamTree.setT24(data24);
            teamTree.setT25(data25);
            teamTree.setT31(data31);
            teamTree.setT32(data32);
            teamTree.setT33(data33);
            teamTree.setT34(data34);
            teamTree.setT35(data35);
            teamTree.setT41(data41);
            teamTree.setT42(data42);
            teamTree.setT43(data43);
            teamTree.setT44(data44);
            teamTree.setT45(data45);
            teamTree.setT51(data51);
            teamTree.setT52(data52);
            teamTree.setT53(data53);
            teamTree.setT54(data54);
            teamTree.setT55(data55);
            iTeamTreeService.insertTeamTree(teamTree);
            model.addAttribute("return", "开团成功！");
        }catch (NullPointerException e){
            System.out.println("空指针异常！");
        }
    }

    @RequestMapping("/edit")
    public void editUserType(HttpServletRequest request){
        String userid = request.getParameter("userid");
        String usertype = request.getParameter("usertype");
        String tId = request.getParameter("tId");

        iTeamMembersService.updateUsertype(tId,usertype,userid);
    }

    @RequestMapping("/editId")
    public void editUserName(HttpServletRequest request){
        String userid = request.getParameter("userid");
        String username = request.getParameter("username");
        String tId = request.getParameter("tId");

        iTeamMembersService.updateUserName(tId,username,userid);
    }

    @RequestMapping("/delTeamMemer")
    public void delTeamMemer(HttpServletRequest request,Model model){
        String userid = request.getParameter("userid");
        String tId = request.getParameter("tId");
        HttpSession session = request.getSession();
        String session_userid = (String) session.getAttribute("userid");
        if (!session_userid.matches("admin")){
            model.addAttribute("delReturn","您没有删除权限，本次操作无效！");
        }else if (session_userid.matches("admin")){
            iTeamMembersService.delTeamMemer(userid,tId);
            model.addAttribute("delReturn","删除成功！");
        }
    }

    @RequestMapping("/updateTeamMembers")
    public void updateTeamMembers(HttpServletRequest request){
        TeamTree teamTree = new TeamTree();
        //ajax传来的
        String t_id = request.getParameter("t_id");
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");
        try {
            String data11 = request.getParameter("data11");
            String data12 = request.getParameter("data12");
            String data13 = request.getParameter("data13");
            String data14 = request.getParameter("data14");
            String data15 = request.getParameter("data15");
            String data21 = request.getParameter("data21");
            String data22 = request.getParameter("data22");
            String data23 = request.getParameter("data23");
            String data24 = request.getParameter("data24");
            String data25 = request.getParameter("data25");
            String data31 = request.getParameter("data31");
            String data32 = request.getParameter("data32");
            String data33 = request.getParameter("data33");
            String data34 = request.getParameter("data34");
            String data35 = request.getParameter("data35");
            String data41 = request.getParameter("data41");
            String data42 = request.getParameter("data42");
            String data43 = request.getParameter("data43");
            String data44 = request.getParameter("data44");
            String data45 = request.getParameter("data45");
            String data51 = request.getParameter("data51");
            String data52 = request.getParameter("data52");
            String data53 = request.getParameter("data53");
            String data54 = request.getParameter("data54");
            String data55 = request.getParameter("data55");

            teamTree.setTId(t_id);
            teamTree.setT11(data11);
            teamTree.setT12(data12);
            teamTree.setT13(data13);
            teamTree.setT14(data14);
            teamTree.setT15(data15);
            teamTree.setT21(data21);
            teamTree.setT22(data22);
            teamTree.setT23(data23);
            teamTree.setT24(data24);
            teamTree.setT25(data25);
            teamTree.setT31(data31);
            teamTree.setT32(data32);
            teamTree.setT33(data33);
            teamTree.setT34(data34);
            teamTree.setT35(data35);
            teamTree.setT41(data41);
            teamTree.setT42(data42);
            teamTree.setT43(data43);
            teamTree.setT44(data44);
            teamTree.setT45(data45);
            teamTree.setT51(data51);
            teamTree.setT52(data52);
            teamTree.setT53(data53);
            teamTree.setT54(data54);
            teamTree.setT55(data55);
            if (userid.matches("admin")) {
                iTeamTreeService.updateTeamMembers(teamTree);
            }
        }catch (NullPointerException e){
            System.out.println("空指针异常！");
        }
    }

    @RequestMapping("/delTeamList")
    public void delTeamList(HttpServletRequest request){
        String id = request.getParameter("id");
        System.out.println(id);
        teamListService.delTeamList(id);
    }
}
