package com.fish.spring5.amqp;

import com.fish.spring5.config.AMQPConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/20
 */
public class AmqpRpcDemo {
    public static void main(String[] args) throws Exception {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(AMQPConfig.class);
        RabbitTemplate rabbitTemplate = ctx.getBean("newsRabbitTemplate", RabbitTemplate.class);
        rabbitTemplate.convertAndSend("FL");
        rabbitTemplate.convertAndSend("MA");
        rabbitTemplate.convertAndSend("CA");
        System.in.read();
        ctx.close();
    }
}
