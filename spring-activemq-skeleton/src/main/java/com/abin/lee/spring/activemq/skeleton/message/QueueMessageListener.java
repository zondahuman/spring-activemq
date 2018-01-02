package com.abin.lee.spring.activemq.skeleton.message;

import com.abin.lee.spring.activemq.skeleton.converter.FeedMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * Created by abin on 2018/1/3 2018/1/3.
 * spring-activemq
 * com.abin.lee.spring.activemq.skeleton.message
 */
@Service
public class QueueMessageListener implements MessageListener {
    final private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Resource
    FeedMessageConverter feedMessageConverter;

    @Transactional
    public void onMessage(Message message) {
        LOGGER.info( "message={}", message);
        try {
            if (message instanceof TextMessage) {
                String objMessage = ((TextMessage) message).getText();
                LOGGER.info("message={}", objMessage);

            } else {
                LOGGER.info("message={}", "不确定类型");
            }
        } catch (JMSException e) {
            LOGGER.error("message={} e.message={} e={}", message,e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            LOGGER.error("message={} e.message={} e={}", message, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}