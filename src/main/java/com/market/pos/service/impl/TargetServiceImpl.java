package com.market.pos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.market.pos.mapper.QAMapper;
import com.market.pos.mapper.TargetMapper;
import com.market.pos.pojo.Qa;
import com.market.pos.pojo.TargetType;
import com.market.pos.service.QAService;
import com.market.pos.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetServiceImpl extends ServiceImpl<TargetMapper, TargetType> implements TargetService {

    @Autowired
    private TargetMapper targetMapper;


    @Override
    public String findTypeByTarget(String target) {
        String type = targetMapper.findTypeByTarget(target);
        return type;
    }

    @Override
    public void insertUserIdAndTarget(String userid, String target, int type) {
        targetMapper.insertUserIdAndTarget(userid, target, type);
    }

    @Override
    public void updateByUserId(int type, String userid) {
        targetMapper.updateByUserId(type, userid);
    }

    @Override
    public void delByUserId(String userid) {
        targetMapper.delByUserId(userid);
    }
}
