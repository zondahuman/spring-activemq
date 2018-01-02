package com.abin.lee.spring.activemq.stub.controller;

import com.abin.lee.spring.activemq.stub.service.MessageSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by abin on 2018/1/3 2018/1/3.
 * spring-activemq
 * com.abin.lee.spring.activemq.stub.controller
 */
@Controller
@RequestMapping("/message")
public class MessageSendController {

    protected final static Logger logger = LoggerFactory.getLogger(MessageSendController.class);



    @Resource
    MessageSendService messageSendService;

    @RequestMapping(value = "/send", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String send(String message) {
        logger.info("message={}", message);
        String flag = "FAILURE";
        try {
            this.messageSendService.send(message);
            flag = "SUCCESS";
        } catch (Exception e) {
            logger.error("e={}", e);
        }
        return flag;
    }


}
