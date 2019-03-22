package com.market.pos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.market.pos.mapper.MembersMapper;
import com.market.pos.pojo.Members;
import com.market.pos.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembersServiceImpl extends ServiceImpl<MembersMapper, Members> implements MembersService {

    @Autowired
    private MembersMapper membersMapper;

    @Override
    public void addMember(Members members) {
        membersMapper.addMember(members);
    }

    @Override
    public String findUser(String userid) {
        String result_userid = membersMapper.findUser(userid);
        return result_userid;
    }

    @Override
    public String findPassword(String userid) {
        String result_password = membersMapper.findPassword(userid);
        return result_password;
    }
}
