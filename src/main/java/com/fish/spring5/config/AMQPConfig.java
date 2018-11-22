package com.fish.spring5.config;

import com.fish.spring5.mq.NewsMessageListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/20
 */
@Configuration
@PropertySource(value = {"classpath:msg.properties"})
public class AMQPConfig {

    @Autowired
    private Environment env;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(env.getProperty("mq.host").trim());
        connectionFactory.setPort(Integer.parseInt(env.getProperty("mq.port").trim()));
        connectionFactory.setVirtualHost(env.getProperty("mq.vhost").trim());
        connectionFactory.setUsername(env.getProperty("mq.username").trim());
        connectionFactory.setPassword(env.getProperty("mq.password").trim());
        return connectionFactory;
    }

    @Bean("newsRabbitTemplate")
    RabbitTemplate newsRabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setChannelTransacted(true);
        rabbitTemplate.setRoutingKey(env.getProperty("mq.news.routingkey").trim());
        rabbitTemplate.setQueue(env.getProperty("mq.news.queue").trim());
        rabbitTemplate.setExchange(env.getProperty("mq.news.exchange").trim());
        rabbitTemplate.setMessageConverter(jackson2MessageConverter());
        rabbitTemplate.setEncoding("UTF-8");
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitAdmin admin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean("newsQueue")
    Queue newsQueue() {
        String name = env.getProperty("mq.news.queue").trim();
        // 是否持久化
        boolean durable = StringUtils.isNotBlank(env.getProperty("mq.news.queue.durable").trim()) ?
                Boolean.valueOf(env.getProperty("mq.news.queue.durable").trim()) : true;
        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = StringUtils.isNotBlank(env.getProperty("mq.news.queue.exclusive").trim()) ?
                Boolean.valueOf(env.getProperty("mq.news.queue.exclusive").trim()) : false;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = StringUtils.isNotBlank(env.getProperty("mq.news.queue.autoDelete").trim()) ?
                Boolean.valueOf(env.getProperty("mq.news.queue.autoDelete").trim()) : false;
        return new Queue(name, durable, exclusive, autoDelete);
    }

    @Bean("newsExchange")
    DirectExchange newsExchange() {
        String name = env.getProperty("mq.news.exchange").trim();
        // 是否持久化
        boolean durable = StringUtils.isNotBlank(env.getProperty("mq.news.exchange.durable").trim()) ?
                Boolean.valueOf(env.getProperty("mq.news.exchange.durable").trim()) : true;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = StringUtils.isNotBlank(env.getProperty("mq.news.exchange.autoDelete").trim()) ?
                Boolean.valueOf(env.getProperty("mq.news.exchange.autoDelete").trim()) : false;
        return new DirectExchange(name, durable, autoDelete);
    }

    @Bean
    Binding newsBinding() {
        String routekey = env.getProperty("mq.news.routingkey").trim();
        return BindingBuilder.bind(newsQueue()).to(newsExchange()).with(routekey);
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer(
            @Qualifier("newsMessageListener") NewsMessageListener newsMessageListener) throws Exception {
        String queueName = env.getProperty("mq.news.queue").trim();

        SimpleMessageListenerContainer simpleMessageListenerContainer =
                new SimpleMessageListenerContainer(connectionFactory());
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(newsMessageListener);
        // 设置手动 ACK
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return simpleMessageListenerContainer;
    }

}
