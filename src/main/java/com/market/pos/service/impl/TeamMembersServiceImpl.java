package com.market.pos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.market.pos.mapper.ITeamMembersMapper;
import com.market.pos.mapper.MembersMapper;
import com.market.pos.pojo.Members;
import com.market.pos.pojo.TeamMembers;
import com.market.pos.service.ITeamMembersService;
import com.market.pos.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamMembersServiceImpl extends ServiceImpl<ITeamMembersMapper, TeamMembers> implements ITeamMembersService {

    @Autowired
    private ITeamMembersMapper iTeamMembersMapper;

    @Override
    public void addTeamMember(TeamMembers teamMembers) {
        iTeamMembersMapper.addTeamMember(teamMembers);
    }

    @Override
    public List<TeamMembers> findById(String tID) {
        List<TeamMembers> list = iTeamMembersMapper.findById(tID);
        return list;
    }
}
