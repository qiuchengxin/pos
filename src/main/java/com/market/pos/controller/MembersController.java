package com.market.pos.controller;

import com.market.pos.pojo.Members;
import com.market.pos.service.MembersService;
import com.market.pos.tool.common.GetUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequestMapping("/Members")
@Controller
public class MembersController {
    static HttpSession session = null;

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
        String QQgroup = request.getParameter("QQgroup");

        GetUserId.getUserId(userid,QQgroup);
        String group_userid = GetUserId.result_userid;

        String result_userid = membersService.findUser(userid);
        //长度校验
        if (!password.equals(password2)){
            model.addAttribute("return","两次输入密码不正确！");
        }else if (password.equals(password2)){
            if (result_userid == null) {
                if (group_userid == null){
                    model.addAttribute("return","你的QQ账号并未在群（"+ QQgroup +"）中，不可以注册哦！");
                }else {
                    members.setUserid(userid);
                    members.setPassword(password);
                    membersService.addMember(members);
                    model.addAttribute("return", "注册成功，即将跳转至登录页面！");
                }
            }else if (result_userid != null){
                model.addAttribute("return","该账号已经被注册了！");
            }
        }
        return "test";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/logincheck" , method = RequestMethod.POST)
    public String logincheck(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String result_userid = membersService.findUser(userid);
        String result_password = membersService.findPassword(userid);

        if (result_userid == null){
            model.addAttribute("return","该账号并未注册！");
        }else {
            if (result_password.equals(password)){
                model.addAttribute("return","登录成功！");
                HttpSession session = request.getSession();
                session.setAttribute("userid",userid);
                response.sendRedirect("/Members/IndexServlet");
            }else {
                model.addAttribute("return","密码错误！");
            }
        }
        return "login";
    }

    @RequestMapping("/IndexServlet")
    public void index(HttpServletRequest request, HttpServletResponse response,Model model)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        session = request.getSession();
        if (session == null){
            response.sendRedirect("/Members/login");
        }
        response.sendRedirect("/main/index");
    }

    @RequestMapping("/LoginOut")
    public void LoginOut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        session = request.getSession();
        //销毁session
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/Members/login");
        session = null ;
    }
}
