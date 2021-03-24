package com.test;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName Main
 * @Description 测试
 * @Author liangxp
 * @Date 2021/1/28 11:17
 **/
public class Main {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("rabbit-context.xml");
        AmqpTemplate amqpTemplate = app.getBean(AmqpTemplate.class);
        amqpTemplate.convertAndSend("MY-QUEUE", "Item");
        String msg = (String) amqpTemplate.receiveAndConvert("MY-QUEUE");
        System.out.println(msg);
    }
}
