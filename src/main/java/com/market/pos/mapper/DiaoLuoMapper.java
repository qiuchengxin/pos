package com.market.pos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.market.pos.pojo.Caidiaoluo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaoLuoMapper extends BaseMapper<Caidiaoluo> {

    public void insertUseridAndTid(long tid, String userid,String name);

    public String selectUserId(long tid, String userid);

    public List<Caidiaoluo> selectAllByTidAndUserId(long tid,String userid);

    public void updatePrice(long tid,String userid,String name,long price);

    public String selectSumOfPrice(long tid,String userid);

    public String selectAllPriceByTid(long tid);

    public String selectOneAllPrice(long tid,String name);
}
