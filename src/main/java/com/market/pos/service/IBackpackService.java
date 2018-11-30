package com.market.pos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.market.pos.pojo.Backpack;

public interface IBackpackService extends IService<Backpack> {

    /**
     * 查询money
     * @param userid
     * @return
     */
    public String selectMoney(int userid);
}
