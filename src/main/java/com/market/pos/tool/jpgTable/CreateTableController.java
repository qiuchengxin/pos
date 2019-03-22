package com.market.pos.tool.jpgTable;

import com.google.gson.Gson;
import com.market.pos.pojo.AskQQMessage;
import com.market.pos.tool.TeamAdmin.TeamAdminService;
import com.market.pos.tool.connect.JdbcCreateTable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateTableController {

    public static void createTable(String groupid,String jpgName) throws Exception {
        String t_from = null;
        if (groupid.equals("721623673")){
            t_from = "皓水";
        }else if (groupid.equals("921340922")){
            t_from = "风波渡";
        }
        TeamAdminService.searchTeamList("pay_data",t_from);
        String tId = TeamAdminService.t_id;
        String t_name = TeamAdminService.t_name;
        String t_type = TeamAdminService.t_type;
        String t_time = TeamAdminService.t_time;
        String liuyan = TeamAdminService.liuyan;
        //参数组装
        String title = t_type + t_name + " " + t_time;
        String remark = "留言：" + liuyan;
        String sql = "select * from team_tree where t_id = " + "'" + tId + "'" ;
        String[][] cellsValue = JdbcCreateTable.searchQA(sql,"pay_data");
        GraphicsGeneration.graphicsGeneration(cellsValue,title,remark,jpgName);
    }

    public static String setJpg(String groupid, String userId) throws Exception {
//        createTable("721623673","1218129325");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String create_time = df.format(new Date());
        String jpgName = create_time + userId;
        createTable(groupid,jpgName);

        AskQQMessage askQQMessage = new AskQQMessage();
        askQQMessage.setAct("101");
        askQQMessage.setQQID(userId);
        askQQMessage.setGroupid(groupid);
        askQQMessage.setMsg("[CQ:at,qq=" + userId + "] 排表如下：" +
                "\n[CQ:image,file=/team_table/" + jpgName + ".jpg]");
        String ask = new Gson().toJson(askQQMessage);
        System.out.println(ask);
        return ask;
    }
}
