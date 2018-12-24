package com.market.pos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.market.pos.pojo.Members;
import com.market.pos.pojo.TimePrice;

import java.util.List;


public interface MembersService extends IService<Members> {

    public void addMember(Members members);

    public String findUser(String userid);

}
