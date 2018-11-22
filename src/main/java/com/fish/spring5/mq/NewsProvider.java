package com.fish.spring5.mq;

import com.fish.spring5.mq.msg.NewsMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/22
 */
@Component
public class NewsProvider {

    private static final Logger logger = LoggerFactory.getLogger(NewsProvider.class);

    @Resource(name = "newsRabbitTemplate")
    private AmqpTemplate rabbitMqTemplate;

    /**
     * 发送延迟消息
     *
     * @param msg        消息内容
     * @param delayTimes 延迟时长，单位：毫秒
     */
    public void sendMessage(NewsMsg msg, final long delayTimes) {
        if (msg != null) {
            logger.info("延迟：{}毫秒写入消息队列：{}，消息内容：{}", delayTimes, msg);
            // 执行发送消息到指定队列
            rabbitMqTemplate.convertAndSend(msg, message -> {
                // 设置延迟毫秒值
                message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
                return message;
            });
        } else {
            logger.warn("消息无效:" + msg);
        }
    }
}

