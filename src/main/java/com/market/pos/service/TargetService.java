package com.market.pos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.market.pos.pojo.TargetType;

import java.util.List;

public interface TargetService extends IService<TargetType> {

    public String findTypeByTarget(String target);

    public void insertUserIdAndTarget(String userid,String target,int type);

    public void updateByUserId(int type,String userid);

    public void delByUserId(String userid);
}
