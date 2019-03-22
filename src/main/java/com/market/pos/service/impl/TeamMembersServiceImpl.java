package com.market.pos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.market.pos.mapper.ITeamMembersMapper;
import com.market.pos.pojo.TeamMembers;
import com.market.pos.service.ITeamMembersService;
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

    @Override
    public String findUserType(String userid,String tId) {
        String userType = iTeamMembersMapper.findUserType(userid,tId);
        return userType;
    }

    @Override
    public List<TeamMembers> findAllByUserType(String tId, String usertype) {
        List<TeamMembers> list = iTeamMembersMapper.findAllByUserType(tId,usertype);
        return list;
    }

    @Override
    public void updateUsertype(String tId, String usertype,String userid) {
        iTeamMembersMapper.updateUsertype(tId, usertype,userid);
    }

    @Override
    public void updateUserName(String tId, String username,String userid) {
        iTeamMembersMapper.updateUserName(tId, username,userid);
    }

    @Override
    public void delTeamMemer(String userid, String tId) {
        iTeamMembersMapper.delTeamMemer(userid,tId);
    }
}
