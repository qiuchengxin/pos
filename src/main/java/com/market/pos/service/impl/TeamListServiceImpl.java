package com.market.pos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.market.pos.mapper.ITeamListMapper;
import com.market.pos.pojo.TeamList;
import com.market.pos.service.TeamListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamListServiceImpl extends ServiceImpl<ITeamListMapper, TeamList> implements TeamListService {

    @Autowired
    private ITeamListMapper iTeamListMapper;

    @Override
    public void insertTeamList(TeamList teamList) {
        iTeamListMapper.insertTeamList(teamList);
    }

    @Override
    public List<TeamList> selectAllTeamList() {
        List<TeamList> list = iTeamListMapper.selectAllTeamList();
        return list;
    }

    @Override
    public List<TeamList> findById(String id) {
        List<TeamList> list = iTeamListMapper.findById(id);
        return list;
    }

    @Override
    public String findTidById(String tFrom) {
        String tId = iTeamListMapper.findTidById(tFrom);
        return tId;
    }


    @Override
    public List<TeamList> findByTid(String tId) {
        List<TeamList> list = iTeamListMapper.findByTid(tId);
        return list;
    }

    @Override
    public void delTeamList(String id) {
        iTeamListMapper.delTeamList(id);
    }
}