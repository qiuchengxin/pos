package com.market.pos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.market.pos.pojo.TeamMembers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ITeamMembersMapper extends BaseMapper<TeamMembers> {

    public void addTeamMember(TeamMembers teamMembers);

    public List<TeamMembers> findById(String tID);
}
