package com.market.pos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.market.pos.pojo.TeamImpormember;

import java.util.List;

public interface ITeamMembersImportService extends IService<TeamImpormember> {

    /**
     * 查询所有重要成员信息
     * @return
     */
    public List<TeamImpormember> findAllByGroupid(String groupid);

    /**
     * 根据groupid和userid删除信息
     * @param userid
     * @param groupid
     */
    public void delByUserId(String userid,String groupid);

    /**
     * update
     */
    public void updateUserNameByUserId(String userid,String username,String groupid);

    /**
     * insert
     */
    public void insertImpotMember(String userid,String username,String groupid);

    /**
     * 验证该用户是否已存在
     * @param userid
     * @param groupid
     * @return
     */
    public String findUserIdByUserIdAndGroupId(String userid,String groupid);

    public String selectUserNameByUserId(String userid,String groupid);
}
