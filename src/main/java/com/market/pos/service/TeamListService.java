package com.market.pos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.market.pos.pojo.TeamList;

public interface TeamListService extends IService<TeamList> {

    public void insertTeamList(TeamList teamList);
}
