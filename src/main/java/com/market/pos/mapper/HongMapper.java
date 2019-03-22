package com.market.pos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.market.pos.pojo.Hong;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HongMapper extends BaseMapper<Hong> {

    public String getHongByUserIdAndType(String userid,String type);

    public void updateHongByUserId(String userid,String type,String content);

    public void insertHong(String userid,String type,String content);
}
