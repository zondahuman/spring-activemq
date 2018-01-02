package com.abin.lee.spring.activemq.stub.util;


import com.abin.lee.spring.activemq.stub.converter.FeedMessageConverter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.io.Serializable;
import java.util.Map;

@Service
public class MessageTaskUtil {
    private static final Logger logger = LoggerFactory
            .getLogger(MessageTaskUtil.class);

    @Resource
    JmsTemplate jmsTemplateQueue;
    @Resource
    Destination queueDestination;
    @Resource
    Destination cmsDestination;
    @Resource
    FeedMessageConverter feedMessageConverter;
    @Resource
    Destination housedelSerialStatusDestination;

    @Resource
    SchedulingTaskExecutor asyncTaskExecutor;


    private void sendQueueMessage(final Serializable obj) {
        // 使用MessageConverter的情况
        jmsTemplateQueue.setMessageConverter(this.feedMessageConverter);
        // 使用MessageConverter的情况
        jmsTemplateQueue.convertAndSend(queueDestination, obj);
    }

    private void sendQueueMessage(Map<String, String> request) {
        // 使用MessageConverter的情况
//        jmsTemplateQueue.setMessageConverter(this.feedMessageConverter);
        // 使用MessageConverter的情况
        jmsTemplateQueue.convertAndSend(queueDestination, request);
    }

    public void sendQueueMessage(String message) {
        // 使用MessageConverter的情况
//        jmsTemplateQueue.setMessageConverter(this.feedMessageConverter);
        // 使用MessageConverter的情况
        jmsTemplateQueue.convertAndSend(queueDestination, message);
    }

    private void sendQueueMessage2CMS(String message) {
        jmsTemplateQueue.convertAndSend(cmsDestination, message);
    }

    private void sendQueueSerialStatusMessage(String message) {
        jmsTemplateQueue.convertAndSend(housedelSerialStatusDestination, message);
    }

    /**
     * 发送消息队列到CMS系统
     *
     * @param c
     * @param name
     * @param msg
     */
    public void sendQueueMessage2CMS(Class c, String name, final String msg) {
        final String key = StringUtils.join(c.getSimpleName(), ":", name);
        final long t1 = System.currentTimeMillis();
        try {
            logger.info("cms queue begin:" + key);
            asyncTaskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    sendQueueMessage2CMS(msg);
                    logger.info("cms queue ok:" + key + ",cost:" + (System.currentTimeMillis() - t1));
                }
            });
        } catch (Exception e) {
            logger.error("cms queue err:" + key, e);
        }

    }

    public void sendQueueMessage(String name, final String msg) {
        final long t1 = System.currentTimeMillis();
        try {
            sendQueueMessage(msg);
        } catch (Exception e) {
            logger.error("feed err:{}", e);
        }

    }

    /**
     * @param c
     * @param name
     * @param msg
     */
    public void sendQueueMessage(Class c, String name, final String msg) {
        final String key = StringUtils.join(c.getSimpleName(), ":", name);
        final long t1 = System.currentTimeMillis();
        try {
            logger.info("feed begin:" + key);
            asyncTaskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    sendQueueMessage(msg);
                    logger.info("feed ok:" + key + ",cost:" + (System.currentTimeMillis() - t1));
                }
            });

        } catch (Exception e) {
            logger.error("feed err:" + key, e);
        }

    }

    public void sendHousedelSerialStatus(Class c, String name, final String msg) {
        final String key = StringUtils.join(c.getSimpleName(), ":", name);
        final long t1 = System.currentTimeMillis();

        try {
            logger.info("serial begin:" + key);
            asyncTaskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    sendQueueSerialStatusMessage(msg);
                    logger.info("serial ok:" + key + ",cost:" + (System.currentTimeMillis() - t1));
                }
            });

        } catch (Exception e) {
            logger.error("serial err:" + key, e);
        }

    }

    public void sendPhoneMessage(Class c, String name, final String phone, final String msg) {
        final String key = StringUtils.join(c.getSimpleName(), ":", name, ",", phone);
        final long t1 = System.currentTimeMillis();
        try {
            logger.info("sendSMS begin:" + key);
            asyncTaskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    logger.info("sendSMS ok:" + key + ",cost:" + (System.currentTimeMillis() - t1));
                }
            });

        } catch (Exception e) {
            logger.error("sendSMS err:" + key, e);
        }

    }
}
