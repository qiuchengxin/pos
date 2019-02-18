package com.market.pos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.market.pos.pojo.Caidiaoluo;

import java.util.List;

public interface DiaoLuoService extends IService<Caidiaoluo> {

    public void insertUseridAndTid(long tid, String userid,String name);

    public String selectUserId(long tid, String userid);

    public List<Caidiaoluo> selectAllByTidAndUserId(long tid, String userid);

    public void updatePrice(long tid,String userid,String name,long price);

    public String selectSumOfPrice(long tid,String userid);

    public String selectAllPriceByTid(long tid);

    public String selectOneAllPrice(long tid,String name);

}
