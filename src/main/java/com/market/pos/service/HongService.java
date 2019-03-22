package com.market.pos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.market.pos.pojo.Hong;

public interface HongService extends IService<Hong> {

    /**
     * 通过userid查询宏
     * @param userid
     * @return
     */
    public String getHongByUserIdAndType(String userid, String type);

    public void updateHongByUserId(String userid,String type,String content);

    public void insertHong(String userid,String type,String content);

}
