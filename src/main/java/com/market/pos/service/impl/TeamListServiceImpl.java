package com.market.pos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.market.pos.mapper.ITeamListMapper;
import com.market.pos.pojo.TeamList;
import com.market.pos.pojo.TeamTree;
import com.market.pos.service.TeamListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamListServiceImpl extends ServiceImpl<ITeamListMapper, TeamList> implements TeamListService {

    @Autowired
    private ITeamListMapper iTeamListMapper;

    @Override
    public void insertTeamList(TeamList teamList) {
        iTeamListMapper.insertTeamList(teamList);
    }
}