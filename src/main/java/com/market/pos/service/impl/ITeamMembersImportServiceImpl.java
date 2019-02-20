package com.market.pos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.market.pos.mapper.TeamMembersImportMapper;
import com.market.pos.pojo.TeamImpormember;
import com.market.pos.service.ITeamMembersImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITeamMembersImportServiceImpl extends ServiceImpl<TeamMembersImportMapper,TeamImpormember> implements ITeamMembersImportService  {

    @Autowired
    private TeamMembersImportMapper teamMembersImportMapper;

    @Override
    public List<TeamImpormember> findAllByGroupid(String groupid) {
        List<TeamImpormember> list = teamMembersImportMapper.findAllByGroupid(groupid);
        return list;
    }

    @Override
    public void delByUserId(String userid, String groupid) {
        teamMembersImportMapper.delByUserId(userid, groupid);
    }

    @Override
    public void updateUserNameByUserId(String userid,String username, String groupid) {
        teamMembersImportMapper.updateUserNameByUserId(userid,username, groupid);
    }

    @Override
    public void insertImpotMember(String userid, String username, String groupid) {
        teamMembersImportMapper.insertImpotMember(userid, username, groupid);
    }

    @Override
    public String findUserIdByUserIdAndGroupId(String userid, String groupid) {
        String resultUserId = teamMembersImportMapper.findUserIdByUserIdAndGroupId(userid, groupid);
        return resultUserId;
    }

    @Override
    public String selectUserNameByUserId(String userid, String groupid) {
        String userName = teamMembersImportMapper.selectUserNameByUserId(userid, groupid);
        return userName;
    }
}
