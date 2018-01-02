package com.abin.lee.spring.activemq.stub.service.impl;

import com.abin.lee.spring.activemq.stub.model.OrderInfo;
import com.abin.lee.spring.activemq.stub.service.MessageSendService;
import com.abin.lee.spring.activemq.stub.service.OrderService;
import com.abin.lee.spring.activemq.stub.util.MessageTaskUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by abin on 2018/1/3 2018/1/3.
 * spring-activemq
 * com.abin.lee.spring.activemq.stub.service.impl
 */
@Service
public class MessageSendServiceImpl implements MessageSendService {

    @Resource
    MessageTaskUtil messageTaskUtil;
    @Resource
    OrderService orderService;

    @Override
    public void send(String message) {
        messageTaskUtil.sendQueueMessage(message);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setFlag("SUCCESS");
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        orderInfo.setVersion(0);
        orderInfo.setUserId("-1");
        this.orderService.insert(orderInfo);
        throw new RuntimeException("Do not insert and rollback insert and message send");
    }
}
