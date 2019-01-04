package com.market.pos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.market.pos.pojo.TeamList;

import java.util.List;

public interface TeamListService extends IService<TeamList> {

    public void insertTeamList(TeamList teamList);

    public List<TeamList> selectAllTeamList();

    public List<TeamList> findById(String id);
}
