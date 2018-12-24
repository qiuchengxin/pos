package com.market.pos.controller;

import com.market.pos.pojo.Members;
import com.market.pos.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/Members")
@Controller
public class MembersController {

    /**
     * 新增member
     */
    @Autowired
    private MembersService membersService;

    @RequestMapping("/test")
    public String members(){
        return "test";
    }

    @RequestMapping(value = "/Add", method = RequestMethod.POST)
    public String addMember(HttpServletRequest request, HttpServletResponse response,Model model) throws IOException {
        Members members = new Members();
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        String result_userid = membersService.findUser(userid);

        if (result_userid == null) {
            members.setUserid(userid);
            members.setPassword(password);
            members.setJob(password2);
            membersService.addMember(members);
            model.addAttribute("return", "注册成功，即将跳转至登录页面！");
        }else if (result_userid != null){
            model.addAttribute("return","该账号已经被注册了！");
        }
        return "test";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
