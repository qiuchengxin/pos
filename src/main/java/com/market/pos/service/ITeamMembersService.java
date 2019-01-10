package com.market.pos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.market.pos.pojo.TeamMembers;

import java.util.List;

public interface ITeamMembersService extends IService<TeamMembers> {

    /**
     * 添加团队成员
     * @param teamMembers
     */
    public void addTeamMember(TeamMembers teamMembers);

    /**
     * 通过团队唯一编码tID查询所有团队成员
     * @param tID
     * @return
     */
    public List<TeamMembers> findById(String tID);

    /**
     * 通过userid获取职业类型
     * @param userid,tId
     * @return
     */
    public String findUserType(String userid,String tId);

    /**
     * 查找指定团队中某职业的所有成员
     * @param tId
     * @param usertype
     * @return
     */
    public List<TeamMembers> findAllByUserType(String tId,String usertype);

    public void updateUsertype(String tId,String usertype,String userid);

    public void updateUserName(String tId,String username,String userid);

    public void delTeamMemer(String userid,String tId);
}
