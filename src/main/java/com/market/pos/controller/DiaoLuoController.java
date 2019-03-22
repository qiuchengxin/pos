package com.market.pos.controller;

import com.market.pos.service.DiaoLuoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/diaoluo")
public class DiaoLuoController {
    private static Logger logger = Logger.getLogger(DiaoLuoController.class);

    @Autowired
    private DiaoLuoService diaoLuoService;

    @RequestMapping("/edit")
    public void editPrice(HttpServletRequest request){
        //通过session获得userid,并向前端传递其时候为管理员的信息
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute("userid");

        String priceString = request.getParameter("price");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        long tid = Long.valueOf(id);
        long price = Long.valueOf(priceString);
        if (price >= 0) {
            diaoLuoService.updatePrice(tid, userid, name, price);
        }
    }
}
