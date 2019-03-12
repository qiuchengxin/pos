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
    public String findTypeByTarget(String target, String tId) {
        String type = targetMapper.findTypeByTarget(target,tId);
        return type;
    }

    @Override
    public void insertUserIdAndTarget(String userid, String target, int type, String tId) {
        targetMapper.insertUserIdAndTarget(userid, target, type, tId);
    }

    @Override
    public void updateByUserId(int type, String userid, String tId) {
        targetMapper.updateByUserId(type, userid, tId);
    }

    @Override
    public void delByUserId(String userid) {
        targetMapper.delByUserId(userid);
    }

    @Override
    public String findByUserId(String userid, String tId) {
        String type = targetMapper.findByUserId(userid, tId);
        return type;
    }
}
