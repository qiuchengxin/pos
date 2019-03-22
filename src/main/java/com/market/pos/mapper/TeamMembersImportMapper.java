package com.market.pos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.market.pos.pojo.TeamImpormember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamMembersImportMapper extends BaseMapper<TeamImpormember> {

    public List<TeamImpormember> findAllByGroupid(String groupid);

    public void delByUserId(String userid,String groupid);

    public void updateUserNameByUserId(String userid,String username,String groupid);

    public void insertImpotMember(String userid,String username,String groupid);

    public String findUserIdByUserIdAndGroupId(String userid,String groupid);

    public String selectUserNameByUserId(String userid,String groupid);
}
