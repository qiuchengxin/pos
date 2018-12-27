package com.market.pos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.market.pos.pojo.TeamTree;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ITeamTreeMapper extends BaseMapper<TeamTree> {

    public void insertTeamTree(TeamTree teamTree);
}
