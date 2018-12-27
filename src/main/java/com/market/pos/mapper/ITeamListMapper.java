package com.market.pos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.market.pos.pojo.TeamList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ITeamListMapper extends BaseMapper<TeamList> {

    public void insertTeamList(TeamList teamList);

    public List<TeamList> selectAllTeamList();
}
