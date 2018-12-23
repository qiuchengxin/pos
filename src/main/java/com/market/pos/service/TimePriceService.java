package com.market.pos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.market.pos.pojo.TimePrice;

import java.util.List;


public interface TimePriceService extends IService<TimePrice> {

    public List<TimePrice> findAll();

    public List<TimePrice> findAsc();

}
