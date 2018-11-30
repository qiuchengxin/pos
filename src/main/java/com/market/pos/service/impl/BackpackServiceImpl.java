package com.market.pos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.market.pos.mapper.IBackpackMapper;
import com.market.pos.pojo.Backpack;
import com.market.pos.service.IBackpackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackpackServiceImpl extends ServiceImpl<IBackpackMapper,Backpack> implements IBackpackService {

    @Autowired
    private IBackpackMapper iBackpackMapper;

    @Override
    public String selectMoney(int userid) {
        return selectMoney(userid);
    }
}
