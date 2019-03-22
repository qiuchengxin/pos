package com.market.pos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.market.pos.pojo.Members;


public interface MembersService extends IService<Members> {

    public void addMember(Members members);

    public String findUser(String userid);

    public String findPassword(String userid);
}
