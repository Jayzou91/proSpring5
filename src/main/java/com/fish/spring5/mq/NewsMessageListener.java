package com.fish.spring5.mq;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fish.spring5.mq.msg.NewsMsg;
import com.fish.spring5.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/20
 */
@Component("newsMessageListener")
public class NewsMessageListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private NewsService newsService;

    @Bean("mappingJackson2MessageConverter")
    public MappingJackson2MessageConverter mappingJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Override
    public void onMessage(Message message) {
        try {
            //spring-amqp Message对象中的Message Properties属性
            MessageProperties messageProperties = message.getMessageProperties();
            //使用Message Converter将spring-amqp Message对象中的Message Properties属性
            String messageContent = new String(message.getBody(), "UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            NewsMsg newsMsg = objectMapper.readValue(messageContent, NewsMsg.class);
            newsService.addNews(newsMsg);
        } catch (UnsupportedEncodingException | JsonParseException | JsonMappingException e) {
            logger.error("onMessage error.", e);
        } catch (IOException e) {
            logger.error("onMessage error.", e);
        }
    }
}
