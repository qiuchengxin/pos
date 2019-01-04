package com.market.pos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.market.pos.pojo.TeamMembers;

import java.util.List;

public interface ITeamMembersService extends IService<TeamMembers> {

    public void addTeamMember(TeamMembers teamMembers);

    public List<TeamMembers> findById(String tID);
}
