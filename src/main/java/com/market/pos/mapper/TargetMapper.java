package com.market.pos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.market.pos.pojo.Qa;
import com.market.pos.pojo.TargetType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TargetMapper extends BaseMapper<TargetType> {

   public String findTypeByTarget(String target);

   public void insertUserIdAndTarget(String userid,String target,int type);

   public void updateByUserId(int type,String userid);

   public void delByUserId(String userid);

}
