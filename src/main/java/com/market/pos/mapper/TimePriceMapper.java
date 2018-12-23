package com.market.pos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.market.pos.pojo.TimePrice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TimePriceMapper extends BaseMapper<TimePrice> {

    /**
     * 查询所有timeprice数据的dao接口
     * @return
     */
    public List<TimePrice> findAll();

    public List<TimePrice> findAsc();

}
