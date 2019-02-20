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

    @Override
    public int selectNumOfPerson(long tid,String name) {
        int numOfPerson = diaoLuoMapper.selectNumOfPerson(tid,name);
        return numOfPerson;
    }

    @Override
    public List<Caidiaoluo> selectJieSuan(long tid, String name,String name2,String name3,String name4) {
        List<Caidiaoluo> list = diaoLuoMapper.selectJieSuan(tid, name, name2, name3, name4);
        return list;
    }

    @Override
    public List<Caidiaoluo> selectOut(long tid) {
        List<Caidiaoluo> list = diaoLuoMapper.selectOut(tid);
        return list;
    }

    @Override
    public String selectPriceByUseridAndTid(long tid, String userid) {
        String price = diaoLuoMapper.selectPriceByUseridAndTid(tid, userid);
        return price;
    }

    @Override
    public List<Caidiaoluo> selectQQId(long tid) {
        List<Caidiaoluo> userIdList = diaoLuoMapper.selectQQId(tid);
        return userIdList;
    }

    @Override
    public void delRecordByTid(long tid) {
        diaoLuoMapper.delRecordByTid(tid);
    }

}
