package com.market.pos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.market.pos.pojo.Qa;

import java.util.List;

public interface QAService extends IService<Qa> {

    public List<Qa> getAllQA();

    public List<Qa> getQAByUserId(String userid);

    public void updateQAById(int id,String answer);

    public void insertQA(String question,String answer,String userid);

    public String getQuestion(String question);

    public void delQA(int id);
}
