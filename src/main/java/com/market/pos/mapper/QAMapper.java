package com.market.pos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.market.pos.pojo.Qa;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QAMapper extends BaseMapper<Qa> {

   public List<Qa> getAllQA();

   public List<Qa> getQAByUserId(String userid);

   public void updateQAById(int id,String answer);

   public void insertQA(String question,String answer,String userid);

   public String getQuestion(String question);

   public void delQA(int id);

}
