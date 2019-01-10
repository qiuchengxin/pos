package com.market.pos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.market.pos.pojo.TeamTree;

public interface ITeamTreeService extends IService<TeamTree> {

    public void insertTeamTree(TeamTree teamTree);

    public void updateTeamMembers(TeamTree teamTree);
}
