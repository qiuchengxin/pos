package com.market.pos.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.market.pos.pojo.Caidiaoluo;
import com.market.pos.pojo.TeamList;
import com.market.pos.pojo.TeamMembers;
import com.market.pos.pojo.TeamTree;
import com.market.pos.service.DiaoLuoService;
import com.market.pos.service.ITeamMembersService;
import com.market.pos.service.JPAService.TeamListRepository;
import com.market.pos.service.JPAService.TeamTreeRepository;
import com.market.pos.service.TeamListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

@Controller
public class TeamInfoByJPA {

    @Autowired
    private TeamListRepository teamListRepository;

    @Autowired
    private TeamTreeRepository teamTreeRepository;

    @Autowired
    private ITeamMembersService iTeamMembersService;

    @Autowired
    private TeamListService teamListService;

    @Autowired
    private DiaoLuoService diaoLuoService;

    @GetMapping(value = "/jpa/{id}")
    public String findAllById(@PathVariable("id") long id , Model model, HttpServletRequest request){
        TeamList teamlist =  teamListRepository.findById(id);
        String tName = teamlist.getTName();
        String tType = teamlist.getTType();
        String tTime = teamlist.getTTime();
        String liuyan = teamlist.getLiuyan();
        String tFrom = teamlist.gettFrom();
        model.addAttribute("t_name",tName);
        model.addAttribute("t_type",tType);
        model.addAttribute("t_time",tTime);
        model.addAttribute("liuyan",liuyan);
        model.addAttribute("tFrom",tFrom);

        String tId = teamlist.getTId();
        Optional<TeamTree> optional = teamTreeRepository.findById(tId);
        TeamTree teamTree = optional.get();

        //获取报名数据
        List<TeamMembers> membersList = iTeamMembersService.findById(tId);
        String jsonString = JSON.toJSONString(membersList);
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
//        System.out.println(jsonArray);
        model.addAttribute("json",jsonArray);
        model.addAttribute("tId",tId);

        //通过session获得userid,并向前端传递其时候为管理员的信息
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");

        if (userid.matches("admin") || userid.matches("382969350")){
            model.addAttribute("isAdmin",1);
        }else if (!userid.matches("admin") && !userid.matches("382969350")){
            model.addAttribute("isAdmin",0);
        }

        String usertype = iTeamMembersService.findUserType(userid,tId);
        if (usertype == null){
            model.addAttribute("usertype","未报名");
        }else {
            model.addAttribute("usertype",usertype);
        }

        //获取table
        String t_11 = teamTree.getT11();
        String t_12 = teamTree.getT12();
        String t_13 = teamTree.getT13();
        String t_14 = teamTree.getT14();
        String t_15 = teamTree.getT15();

        String t_21 = teamTree.getT21();
        String t_22 = teamTree.getT22();
        String t_23 = teamTree.getT23();
        String t_24 = teamTree.getT24();
        String t_25 = teamTree.getT25();

        String t_31 = teamTree.getT31();
        String t_32 = teamTree.getT32();
        String t_33 = teamTree.getT33();
        String t_34 = teamTree.getT34();
        String t_35 = teamTree.getT35();

        String t_41 = teamTree.getT41();
        String t_42 = teamTree.getT42();
        String t_43 = teamTree.getT43();
        String t_44 = teamTree.getT44();
        String t_45 = teamTree.getT45();

        String t_51 = teamTree.getT51();
        String t_52 = teamTree.getT52();
        String t_53 = teamTree.getT53();
        String t_54 = teamTree.getT54();
        String t_55 = teamTree.getT55();

        model.addAttribute("t_11", t_11);
        model.addAttribute("t_12", t_12);
        model.addAttribute("t_13", t_13);
        model.addAttribute("t_14", t_14);
        model.addAttribute("t_15", t_15);

        model.addAttribute("t_21", t_21);
        model.addAttribute("t_22", t_22);
        model.addAttribute("t_23", t_23);
        model.addAttribute("t_24", t_24);
        model.addAttribute("t_25", t_25);

        model.addAttribute("t_31", t_31);
        model.addAttribute("t_32", t_32);
        model.addAttribute("t_33", t_33);
        model.addAttribute("t_34", t_34);
        model.addAttribute("t_35", t_35);

        model.addAttribute("t_41", t_41);
        model.addAttribute("t_42", t_42);
        model.addAttribute("t_43", t_43);
        model.addAttribute("t_44", t_44);
        model.addAttribute("t_45", t_45);

        model.addAttribute("t_51", t_51);
        model.addAttribute("t_52", t_52);
        model.addAttribute("t_53", t_53);
        model.addAttribute("t_54", t_54);
        model.addAttribute("t_55", t_55);
        return "teammade";
    }

    @GetMapping(value = "/teamJoin/{id}")
    public String teamJoin(@PathVariable("id") long id,Model model){
        model.addAttribute("id",id);
        return "baoming";
    }

    @GetMapping(value = "/diaoluo/{id}")
    public String diaoluo(@PathVariable("id") long id,Model model,HttpServletRequest request){
        model.addAttribute("id",id);
        //通过session获得userid,并向前端传递其时候为管理员的信息
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");
        //获取副本信息
        TeamList teamlist = teamListRepository.findById(id);
        String tName = teamlist.getTName();
        String tType = teamlist.getTType();
        String tTime = teamlist.getTTime();
        String Name = tType + tName;
        model.addAttribute("Name",Name);
        model.addAttribute("tTime",tTime);

        String resultUserId = diaoLuoService.selectUserId(id,userid);
        String[] packName = {"归墟玄晶","肃杀衣","肃风衣","肃和衣","肃雍衣","昭懿裤","昭清裤","昭武裤","昭灼裤","昭苏裤","昭仰裤","昭懿戒","昭清戒","昭武戒","昭灼戒","昭苏戒","昭仰戒"};
        if (resultUserId == null){
            //如果用户第一次点开此按钮，则生成用户对应的数据
            for (int i = 0 ; i<=packName.length;i++){
                diaoLuoService.insertUseridAndTid(id,userid,packName[i]);
            }
        }

        //查询当前已压金额
        String sumOfPrice = diaoLuoService.selectSumOfPrice(id,userid);
        model.addAttribute("sumOfPrice",sumOfPrice);

        List<Caidiaoluo> list = diaoLuoService.selectAllByTidAndUserId(id,userid);
        String jsonString = JSON.toJSONString(list);
        JSONArray jsonArray = JSONArray.parseArray(jsonString);

        //计算赔率
        JSONArray json = new JSONArray();

        for (int i = 0 ; i<=packName.length;i++){
            JSONObject jsonObject = new JSONObject();
            String allPrice = diaoLuoService.selectAllPriceByTid(id);
            String oneAllPrice = diaoLuoService.selectOneAllPrice(id,packName[i]);

            if (allPrice == null && oneAllPrice == null){
                jsonObject = jsonArray.getJSONObject(i);
                jsonObject.put("peilv",0);
            }else if (oneAllPrice == null && allPrice != null){
                jsonObject = jsonArray.getJSONObject(i);
                jsonObject.put("peilv",allPrice);
            }else {
                int allPriceInt = Integer.parseInt(allPrice);
                int oneAllPriceInt = Integer.parseInt(oneAllPrice);
                double peilv = allPriceInt / oneAllPriceInt;
                jsonObject = jsonArray.getJSONObject(i);
                jsonObject.put("peilv",peilv);
            }
            json.add(jsonObject);
        }
        System.out.println(json.toString());
        model.addAttribute("json",json);
        return "diaoluo";
    }

    @GetMapping("/teamTable/{tFrom}")
    public String teamTable(Model model,@PathVariable("tFrom") String tFrom){
        String teamName = null;
        if (tFrom.equals("hs")){
            teamName = "皓水";
        }else if (tFrom.equals("fbd")){
            teamName = "风波渡";
        }

        String tId = teamListService.findTidById(teamName);
        System.out.println(tId);
        Optional<TeamTree> optional = teamTreeRepository.findById(tId);
        TeamTree teamTree = optional.get();

        TeamList teamlist =  teamListRepository.findByTId(tId);
        String tName = teamlist.getTName();
        String tType = teamlist.getTType();
        String tTime = teamlist.getTTime();
        String liuyan = teamlist.getLiuyan();
        model.addAttribute("t_from",teamName);
        model.addAttribute("t_name",tName);
        model.addAttribute("t_type",tType);
        model.addAttribute("t_time",tTime);
        model.addAttribute("liuyan",liuyan);

        //获取table
        String t_11 = teamTree.getT11();
        String t_12 = teamTree.getT12();
        String t_13 = teamTree.getT13();
        String t_14 = teamTree.getT14();
        String t_15 = teamTree.getT15();

        String t_21 = teamTree.getT21();
        String t_22 = teamTree.getT22();
        String t_23 = teamTree.getT23();
        String t_24 = teamTree.getT24();
        String t_25 = teamTree.getT25();

        String t_31 = teamTree.getT31();
        String t_32 = teamTree.getT32();
        String t_33 = teamTree.getT33();
        String t_34 = teamTree.getT34();
        String t_35 = teamTree.getT35();

        String t_41 = teamTree.getT41();
        String t_42 = teamTree.getT42();
        String t_43 = teamTree.getT43();
        String t_44 = teamTree.getT44();
        String t_45 = teamTree.getT45();

        String t_51 = teamTree.getT51();
        String t_52 = teamTree.getT52();
        String t_53 = teamTree.getT53();
        String t_54 = teamTree.getT54();
        String t_55 = teamTree.getT55();

        model.addAttribute("t_11", t_11);
        model.addAttribute("t_12", t_12);
        model.addAttribute("t_13", t_13);
        model.addAttribute("t_14", t_14);
        model.addAttribute("t_15", t_15);

        model.addAttribute("t_21", t_21);
        model.addAttribute("t_22", t_22);
        model.addAttribute("t_23", t_23);
        model.addAttribute("t_24", t_24);
        model.addAttribute("t_25", t_25);

        model.addAttribute("t_31", t_31);
        model.addAttribute("t_32", t_32);
        model.addAttribute("t_33", t_33);
        model.addAttribute("t_34", t_34);
        model.addAttribute("t_35", t_35);

        model.addAttribute("t_41", t_41);
        model.addAttribute("t_42", t_42);
        model.addAttribute("t_43", t_43);
        model.addAttribute("t_44", t_44);
        model.addAttribute("t_45", t_45);

        model.addAttribute("t_51", t_51);
        model.addAttribute("t_52", t_52);
        model.addAttribute("t_53", t_53);
        model.addAttribute("t_54", t_54);
        model.addAttribute("t_55", t_55);

        return "kanpaibiao";
    }
}