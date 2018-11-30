package com.market.pos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.market.pos.pojo.Backpack;

public interface IBackpackMapper extends BaseMapper<Backpack> {

    /**
     * 通过userid删除数据
     * @param userid
     */
    public void delBackpack(int userid);

    /**
     * 通过userid查询money
     * @param userid
     * @return
     */
    public String selectMoney(int userid);

}
