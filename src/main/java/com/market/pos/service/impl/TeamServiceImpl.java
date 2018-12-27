package com.market.pos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.market.pos.mapper.ITeamTreeMapper;
import com.market.pos.pojo.TeamTree;
import com.market.pos.service.ITeamTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl extends ServiceImpl<ITeamTreeMapper, TeamTree> implements ITeamTreeService {

    @Autowired
    private ITeamTreeMapper iTeamTreeMapper;

    @Override
    public void insertTeamTree(TeamTree teamTree) {
        iTeamTreeMapper.insertTeamTree(teamTree);
    }
}
