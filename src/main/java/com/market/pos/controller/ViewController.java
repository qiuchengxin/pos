package com.market.pos.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.market.pos.pojo.Qa;
import com.market.pos.pojo.TeamImpormember;
import com.market.pos.pojo.TeamList;
import com.market.pos.service.ITeamMembersImportService;
import com.market.pos.service.QAService;
import com.market.pos.service.TeamListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @Autowired
    private QAService qaService;

    @Autowired
    private ITeamMembersImportService iTeamMembersImportService;

    @RequestMapping("/make")
    public String teamMake(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");
        if (!userid.equals("admin") && !userid.equals("382969350")){
            response.sendRedirect("/main/403");
        }
        return "teammake";
    }

    @RequestMapping("/made")
    public String teamMade(){
        return "teammade";
    }

    @RequestMapping("/teamList")
    public String teamList(Model model, HttpServletRequest request){
        List<TeamList> list = teamListService.selectAllTeamList();
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");
        int isAdmin = 0;
        if (userid.matches("admin") || userid.matches("382969350")){
            isAdmin = 1;
        }else if (! userid.matches("admin") && !userid.matches("382969350")){
            isAdmin = 0;
        }
        model.addAttribute("isAdmin",isAdmin);
        model.addAttribute("list",list);
        return "teamlist";
    }

    @RequestMapping("/suc")
    public String suc(Model model){
        model.addAttribute("return","招募发布成功！");
        return "suc";
    }

    @RequestMapping("/filedemo")
    public String file(){
        return "file";
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

    @RequestMapping("/hong")
    public String hong(){ return "hong"; }

    @RequestMapping("/cygl")
    public String cygl(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String userid = String.valueOf(session.getAttribute("userid"));

        String groupid = null;
        String tFrom = null;
        if (!userid.equals("admin") && !userid.equals("382969350")){
            response.sendRedirect("/main/403");
        }else if (userid.equals("admin")){
            groupid = "721623673";
            tFrom = "皓水";
        }else if (userid.equals("382969350")){
            groupid = "921340922";
            tFrom = "风波渡";
        }

        List<TeamImpormember> list = iTeamMembersImportService.findAllByGroupid(groupid);
        String jsonString = JSON.toJSONString(list);
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        model.addAttribute("json",jsonArray);
        model.addAttribute("tFrom",tFrom);
        return "cygl";
    }

    @RequestMapping(value = "/insertImpotMember" , method = RequestMethod.POST)
    public String insertImpotMember(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        String userid = String.valueOf(session.getAttribute("userid"));

        String groupid = null;
        String tFrom = null;
        if (userid.equals("admin")){
            groupid = "721623673";
            tFrom = "皓水";
        }else if (userid.equals("382969350")){
            groupid = "921340922";
            tFrom = "风波渡";
        }

        String usernameGet = request.getParameter("username");
        String useridGet = request.getParameter("userid");

        String resultUserId = iTeamMembersImportService.findUserIdByUserIdAndGroupId(useridGet,groupid);
        //只在无用户情况下新增用户
        if (resultUserId == null) {
            iTeamMembersImportService.insertImpotMember(useridGet, usernameGet, groupid);
            model.addAttribute("return","新增成员成功！");
        }else {
            model.addAttribute("return","该成员已经添加过了！");
        }

        List<TeamImpormember> list = iTeamMembersImportService.findAllByGroupid(groupid);
        String jsonString = JSON.toJSONString(list);
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        model.addAttribute("json",jsonArray);
        model.addAttribute("tFrom",tFrom);

        return "cygl";
    }

    @RequestMapping("/calculate")
    public String calculate(){
        return "calculate";
    }

    @RequestMapping(value = "/insertQA" , method = RequestMethod.POST)
    public String insertQA(HttpServletRequest request,Model model){

        //获取session中的userid，确认用户身份
        HttpSession session = request.getSession();
        String userid = String.valueOf(session.getAttribute("userid"));

        String question = request.getParameter("question");
        String answer = request.getParameter("answer");
        if (question.equals("") || answer.equals("")){
            model.addAttribute("return","问题和答案不能为空");
        }else {
            String resultQuestion = qaService.getQuestion(question);
            //只在无相同题目情况下新增QA
            if (resultQuestion == null) {
                qaService.insertQA(question, answer,userid);
                model.addAttribute("return", "新增QA成功！");
            } else {
                model.addAttribute("return", "该问题已经添加过了！");
            }
        }

        if (userid.equals("admin")) {
            List<Qa> list = qaService.getAllQA();
            String jsonString = JSON.toJSONString(list);
            JSONArray jsonArray = JSONArray.parseArray(jsonString);
            model.addAttribute("json", jsonArray);
        }else {
            List<Qa> list = qaService.getQAByUserId(userid);
            String jsonString = JSON.toJSONString(list);
            JSONArray jsonArray = JSONArray.parseArray(jsonString);
            model.addAttribute("json", jsonArray);
        }

        return "QA";
    }
}
