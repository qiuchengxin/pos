package com.market.pos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.market.pos.mapper.TimePriceMapper;
import com.market.pos.pojo.TimePrice;
import com.market.pos.service.TimePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimePriceServiceImpl extends ServiceImpl<TimePriceMapper, TimePrice> implements TimePriceService {
    @Autowired
    private TimePriceMapper timePriceMapper;

    /**
     * 获取timeprice所有数据的实现类
     * @return
     */
    @Override
    public List<TimePrice> findAll() {
        List<TimePrice> list = timePriceMapper.findAll();
        return list;
    }
}
