package com.market.pos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.market.pos.mapper.DiaoLuoMapper;
import com.market.pos.pojo.Caidiaoluo;
import com.market.pos.service.DiaoLuoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaoLuoServiceImpl extends ServiceImpl<DiaoLuoMapper, Caidiaoluo> implements DiaoLuoService {

    @Autowired
    DiaoLuoMapper diaoLuoMapper;

    @Override
    public void insertUseridAndTid(long tid, String userid,String name) {
        diaoLuoMapper.insertUseridAndTid(tid, userid,name);
    }

    @Override
    public String selectUserId(long tid, String userid) {
        String resultUserId = diaoLuoMapper.selectUserId(tid, userid);
        return resultUserId;
    }

    @Override
    public List<Caidiaoluo> selectAllByTidAndUserId(long tid, String userid) {
        List<Caidiaoluo> list = diaoLuoMapper.selectAllByTidAndUserId(tid, userid);
        return list;
    }

    @Override
    public void updatePrice(long tid, String userid, String name,long price) {
        diaoLuoMapper.updatePrice(tid, userid, name,price);
    }

    @Override
    public String selectSumOfPrice(long tid, String userid) {
        String sumOfPrice = diaoLuoMapper.selectSumOfPrice(tid, userid);
        return sumOfPrice;
    }

    @Override
    public String selectAllPriceByTid(long tid) {
        String allPrice = diaoLuoMapper.selectAllPriceByTid(tid);
        return allPrice;
    }

    @Override
    public String selectOneAllPrice(long tid, String name) {
        String oneAllPrice = diaoLuoMapper.selectOneAllPrice(tid, name);
        return oneAllPrice;
    }
}
