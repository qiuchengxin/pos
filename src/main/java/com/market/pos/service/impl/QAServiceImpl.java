package com.market.pos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.market.pos.mapper.HongMapper;
import com.market.pos.mapper.QAMapper;
import com.market.pos.pojo.Hong;
import com.market.pos.pojo.Qa;
import com.market.pos.service.HongService;
import com.market.pos.service.QAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QAServiceImpl extends ServiceImpl<QAMapper, Qa> implements QAService {

    @Autowired
    private QAMapper qaMapper;

    @Override
    public List<Qa> getAllQA() {
        List<Qa> list = qaMapper.getAllQA();
        return list;
    }

    @Override
    public List<Qa> getQAByUserId(String userid) {
        List<Qa> list = qaMapper.getQAByUserId(userid);
        return list;
    }

    @Override
    public void updateQAById(int id,String answer) {
        qaMapper.updateQAById(id,answer);
    }

    @Override
    public void insertQA(String question, String answer,String userid) {
        qaMapper.insertQA(question,answer,userid);
    }

    @Override
    public String getQuestion(String question) {
        String resultQuestion = qaMapper.getQuestion(question);
        return resultQuestion;
    }

    @Override
    public void delQA(int id) {
        qaMapper.delQA(id);
    }
}
