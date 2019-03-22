package com.market.pos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.market.pos.mapper.HongMapper;
import com.market.pos.pojo.Hong;
import com.market.pos.service.HongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HongServiceImpl extends ServiceImpl<HongMapper, Hong> implements HongService {

    @Autowired
    private HongMapper hongMapper;

    @Override
    public String getHongByUserIdAndType(String userid,String type) {
        String list = hongMapper.getHongByUserIdAndType(userid,type);
        return list;
    }

    @Override
    public void updateHongByUserId(String userid, String type,String content) {
        hongMapper.updateHongByUserId(userid, type,content);
    }

    @Override
    public void insertHong(String userid, String type, String content) {
        hongMapper.insertHong(userid, type, content);
    }
}
