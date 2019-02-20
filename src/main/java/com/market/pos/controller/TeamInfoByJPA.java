package com.market.pos.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.market.pos.config.CommonReplaceNull;
import com.market.pos.pojo.Caidiaoluo;
import com.market.pos.pojo.TeamList;
import com.market.pos.pojo.TeamMembers;
import com.market.pos.pojo.TeamTree;
import com.market.pos.service.DiaoLuoService;
import com.market.pos.service.ITeamMembersImportService;
import com.market.pos.service.ITeamMembersService;
import com.market.pos.service.JPAService.TeamListRepository;
import com.market.pos.service.JPAService.TeamTreeRepository;
import com.market.pos.service.TeamListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private ITeamMembersImportService iTeamMembersImportService;

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
    public String diaoluo(@PathVariable("id") long id,Model model,HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
        model.addAttribute("id",id);
        //通过session获得userid,并向前端传递其时候为管理员的信息
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");
        if ("admin".equals(userid)){
            model.addAttribute("userid",1);
        }else {
            model.addAttribute("userid",0);
        }

        //获取副本信息
        TeamList teamlist = teamListRepository.findById(id);
        String tName = teamlist.getTName();
        String tType = teamlist.getTType();
        String tTime = teamlist.getTTime();
        //获取更新时间 update_time
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH点mm分");
        String update_time = df.format(new Date());
        Date nowDate = df.parse(update_time);
        Date tDate = df.parse(tTime);
        long nowDateLong = nowDate.getTime();
        long tDateLong = tDate.getTime();
        long k = tDateLong - nowDateLong;

        if (k >= 0){
            model.addAttribute("k",1);
        }else if (k < 0){
            model.addAttribute("k",0);
        }

        String Name = tType + tName;
        model.addAttribute("Name",Name);
        model.addAttribute("tTime",tTime);

        String resultUserId = diaoLuoService.selectUserId(id,userid);
        String[] packName = {"归墟玄晶","肃杀衣","肃风衣","肃和衣","肃雍衣","肃然衣","肃澹衣","昭懿裤","昭清裤","昭武裤","昭灼裤","昭苏裤","昭仰裤","昭懿戒","昭清戒","昭武戒","昭灼戒","昭苏戒","昭仰戒"};

        JSONArray json = new JSONArray();
        if (resultUserId == null){
            //如果用户第一次点开此按钮，则生成用户对应的数据
            for (int i = 0 ; i<packName.length;i++){
                JSONObject jsonObject = new JSONObject();
                //插入数据
                diaoLuoService.insertUseridAndTid(id,userid,packName[i]);
                jsonObject.put("name",packName[i]);
                jsonObject.put("price","0");
                jsonObject.put("peilv","0");
                jsonObject.put("num", "待刷新");
                json.add(jsonObject);
            }
            model.addAttribute("sumOfPrice", "0金");
        }else {
            //查询当前已压金额
            String sumOfPrice = diaoLuoService.selectSumOfPrice(id, userid);
            if (sumOfPrice == null) {
                sumOfPrice = "0";
            }
            sumOfPrice = sumOfPrice + "金";
            model.addAttribute("sumOfPrice", sumOfPrice);

            List<Caidiaoluo> list = diaoLuoService.selectAllByTidAndUserId(id, userid);
            String jsonString = JSON.toJSONString(list);
            JSONArray jsonArray = JSONArray.parseArray(jsonString);
            //计算赔率
            for (int i = 0; i < packName.length; i++) {
                JSONObject jsonObject = new JSONObject();
                String allPrice = diaoLuoService.selectAllPriceByTid(id);
                String oneAllPrice = diaoLuoService.selectOneAllPrice(id, packName[i]);

                if (allPrice == null && oneAllPrice == null) { //都为空
                    jsonObject = jsonArray.getJSONObject(i);
                    jsonObject.put("peilv", "0");
                    String name = (String) jsonObject.get("name");
                    int num = diaoLuoService.selectNumOfPerson(id, name);
                    jsonObject.put("num", num);
                } else if (oneAllPrice == null && allPrice != null) {//还没人选择
                    jsonObject = jsonArray.getJSONObject(i);
                    int allPriceInt = Integer.parseInt(allPrice);
                    if (allPriceInt == 0) {
                        jsonObject.put("peilv", "0");
                        String name = (String) jsonObject.get("name");
                        int num = diaoLuoService.selectNumOfPerson(id, name);
                        jsonObject.put("num", num);
                    } else {
                        jsonObject.put("peilv", "无人投此项");
                        String name = (String) jsonObject.get("name");
                        int num = diaoLuoService.selectNumOfPerson(id, name);
                        jsonObject.put("num", num);
                    }
                } else {//有人选择了
                    jsonObject = jsonArray.getJSONObject(i);
                    int allPriceInt = Integer.parseInt(allPrice);
                    int oneAllPriceInt = Integer.parseInt(oneAllPrice);
                    if (allPriceInt == 0 && oneAllPriceInt == 0) {
                        jsonObject.put("peilv", "无人投此项");
                        String name = (String) jsonObject.get("name");
                        int num = diaoLuoService.selectNumOfPerson(id, name);
                        jsonObject.put("num", num);
                    } else if (allPriceInt != 0 && oneAllPriceInt == 0) {
                        jsonObject.put("peilv", "无人投此项");
                        String name = (String) jsonObject.get("name");
                        int num = diaoLuoService.selectNumOfPerson(id, name);
                        jsonObject.put("num", num);
                    } else {
                        double peilv = allPriceInt / oneAllPriceInt;
                        jsonObject.put("peilv", peilv);
                        String name = (String) jsonObject.get("name");
                        int num = diaoLuoService.selectNumOfPerson(id, name);
                        jsonObject.put("num", num);
                    }
                }
                json.add(jsonObject);
            }
        }
        System.out.println(json);
        model.addAttribute("json",json);
        return "diaoluo";
    }

    @GetMapping(value = "/jiesuan/{id}")
    public String jiesuan(@PathVariable("id") long id,Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
        model.addAttribute("id",id);
        //通过session获得userid,并向前端传递其时候为管理员的信息
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");
        if (!"admin".equals(userid)){
            response.sendRedirect("/main/index");
        }
        return "jiesuan";
    }

    @GetMapping(value = "/jiesuando/{id}")
    public String jiesuanDo(@PathVariable("id") long id,Model model,HttpServletRequest request) {
        model.addAttribute("id",id);
        String sxuanjing = request.getParameter("xuanjing");
        String skuzi = request.getParameter("kuzi");
        String syifu = request.getParameter("yifu");
        String sjiezhi = request.getParameter("jiezhi");

        String xuanjing = CommonReplaceNull.commonReplaceNull(sxuanjing);
        String kuzi = CommonReplaceNull.commonReplaceNull(skuzi);
        String yifu = CommonReplaceNull.commonReplaceNull(syifu);
        String jiezhi = CommonReplaceNull.commonReplaceNull(sjiezhi);

        List<Caidiaoluo> list = diaoLuoService.selectJieSuan(id,xuanjing,kuzi,yifu,jiezhi);
        String jsonString = JSON.toJSONString(list);
        JSONArray jsonArray = JSONArray.parseArray(jsonString);

        List<Caidiaoluo> listAll = diaoLuoService.selectOut(id);
        String jsonStringAll = JSON.toJSONString(listAll);
        JSONArray jsonArrayAll = JSONArray.parseArray(jsonStringAll);

        //计算奖金
        JSONArray json = new JSONArray();
        if (jsonArray.size() != 0){
            //计算总金团
            String allPrice = diaoLuoService.selectAllPriceByTid(id);
            int allPriceInt = Integer.parseInt(allPrice);

            for (int i=0;i<jsonArray.size();i++){
                JSONObject jsonObjectArray = jsonArray.getJSONObject(i);
                String name = (String) jsonObjectArray.get("name");
                String userid = (String) jsonObjectArray.get("userid");
                int priceInt = (int) jsonObjectArray.get("price");
                JSONObject jsonObject = new JSONObject();

                //获取username
                String userName = iTeamMembersImportService.selectUserNameByUserId(userid,"721623673");
                if (userName == null){
                    userName = "暂无此人信息";
                }

                if ("归墟玄晶".equals(name)){
                    jsonObject = jsonArray.getJSONObject(i);
                    //allprice为此部分总奖金
                    //选玄晶的总金额
                    String oneAllPrice = diaoLuoService.selectOneAllPrice(id,name);
                    int oneAllPriceInt = Integer.parseInt(oneAllPrice);
                    //奖金
                    double jiangjin = ( (double) priceInt / (double)oneAllPriceInt ) * allPriceInt;
                    jsonObject.put("jiangjin",jiangjin);
                    jsonObject.put("userName",userName);
                }else if (name.matches(".*衣")){
                    jsonObject = jsonArray.getJSONObject(i);
                    String oneAllPrice = diaoLuoService.selectOneAllPrice(id,name);
                    int oneAllPriceInt = Integer.parseInt(oneAllPrice);
                    //奖金
                    double jiangjin = ( (double)priceInt / (double)oneAllPriceInt ) * (allPriceInt * 0.33);
                    jsonObject.put("jiangjin",jiangjin);
                    jsonObject.put("userName",userName);
                }else if (name.matches(".*裤")){
                    jsonObject = jsonArray.getJSONObject(i);
                    String oneAllPrice = diaoLuoService.selectOneAllPrice(id,name);
                    int oneAllPriceInt = Integer.parseInt(oneAllPrice);
                    //奖金
                    double jiangjin = ( (double)priceInt / (double)oneAllPriceInt ) * (allPriceInt * 0.33);
                    jsonObject.put("jiangjin",jiangjin);
                    jsonObject.put("userName",userName);
                }else if (name.matches(".*戒")){
                    jsonObject = jsonArray.getJSONObject(i);
                    String oneAllPrice = diaoLuoService.selectOneAllPrice(id,name);
                    int oneAllPriceInt = Integer.parseInt(oneAllPrice);
                    //奖金
                    double jiangjin = ( (double)priceInt / (double)oneAllPriceInt ) * (allPriceInt * 0.33);
                    jsonObject.put("jiangjin",jiangjin);
                    jsonObject.put("userName",userName);
                }
                json.add(jsonObject);
            }
        }
        model.addAttribute("json",json);

        JSONArray jsonAll = new JSONArray();
        for (int i = 0;i<jsonArrayAll.size();i++){
            JSONObject jsonObject = jsonArrayAll.getJSONObject(i);
            String userid = (String) jsonObject.get("userid");
            String username = iTeamMembersImportService.selectUserNameByUserId(userid,"721623673");
            if (username == null){
                username = "暂无此人信息";
            }
            jsonObject.put("userName",username);
            jsonAll.add(jsonObject);
        }
        model.addAttribute("jsonAll",jsonAll);

        //通过userid合并账单
        JSONArray jsonByUserId = new JSONArray();
        List<Caidiaoluo> userIdList = diaoLuoService.selectQQId(id);
        for (int i =0;i<userIdList.size();i++){
            JSONObject jsonObject = new JSONObject();
            Caidiaoluo caidiaoluo = userIdList.get(i);
            String userId = caidiaoluo.getUserid();
            //获取username
            String userName = iTeamMembersImportService.selectUserNameByUserId(userId,"721623673");
            if (userName == null){
                userName = "暂无此人信息";
            }
            //通过userid查询总账单
            String sumOfPriceByUserId = diaoLuoService.selectSumOfPrice(id,userId);
            jsonObject.put("sumOfPrice",sumOfPriceByUserId);
            jsonObject.put("userId",userId);
            jsonObject.put("userName",userName);
            jsonByUserId.add(jsonObject);
        }
        model.addAttribute("jsonByUserId",jsonByUserId);
        return "jiesuantable";
    }

    @GetMapping("/teamTable/{tFrom}")
    public String teamTable(Model model,@PathVariable("tFrom") String tFrom){
        String teamName = null;
        if ("hs".equals(tFrom)){
            teamName = "皓水";
        }else if ("fbd".equals(tFrom)){
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