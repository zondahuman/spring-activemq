package com.abin.lee.spring.activemq.stub.service.impl;

import com.abin.lee.spring.activemq.stub.dao.OrderInfoMapper;
import com.abin.lee.spring.activemq.stub.model.OrderInfo;
import com.abin.lee.spring.activemq.stub.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by abin on 2018/1/3 2018/1/3.
 * spring-activemq
 * com.abin.lee.spring.activemq.stub.service.impl
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderInfoMapper orderInfoMapper;
    @Override
    public void insert(OrderInfo model) {
        this.orderInfoMapper.insert(model);
    }
}
