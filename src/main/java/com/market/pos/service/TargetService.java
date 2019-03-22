package com.market.pos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.market.pos.pojo.TargetType;

public interface TargetService extends IService<TargetType> {

    public String findTypeByTarget(String target,String tId);

    public void insertUserIdAndTarget(String userid,String target,int type,String tId);

    public void updateByUserId(int type,String userid,String tId);

    public void delByUserId(String userid);

    public String findByUserId(String userid,String tId);

}
