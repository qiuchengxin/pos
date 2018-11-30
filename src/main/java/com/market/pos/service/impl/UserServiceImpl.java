package com.market.pos.service.impl;import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;import com.market.pos.mapper.IUserMapper;import com.market.pos.pojo.Users;import com.market.pos.service.IUserService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.List;@Servicepublic class UserServiceImpl extends ServiceImpl<IUserMapper, Users> implements IUserService {    @Autowired    private IUserMapper iUserMapper;    @Override    public List<Users> findAllUsers(){        List<Users> list = iUserMapper.getAllUser();        return list;    }    @Override    public List<Users> addUser(Users users) {        iUserMapper.insertUser(users);        return null;    }    @Override    public List<Users> updateUser(Users users) {        iUserMapper.updateUser(users);        return null;    }}