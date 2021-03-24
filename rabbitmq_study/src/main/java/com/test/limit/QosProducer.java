package com.test.limit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName QosProducer
 * @Description RabbitMQ 消费端限流 生产者
 * @Author liangxp
 * @Date 2021/3/24 17:54
 **/
public class QosProducer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1. 创建一个 ConnectionFactory 并进行设置
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setVirtualHost("/");
        factory.setUsername("guest");
        factory.setPassword("guest");

        //2. 通过连接工厂来创建连接
        Connection connection = factory.newConnection();

        //3. 通过 Connection 来创建 Channel
        Channel channel = connection.createChannel();

        //4. 声明
        String exchangeName = "test_qos_exchange";
        String routingKey = "item.add";

        //5. 发送
        String msg = "this is qos msg";
        for (int i = 0; i < 30; i++) {
            String tem = msg + " : " + i;
            channel.basicPublish(exchangeName, routingKey, null, tem.getBytes());
            System.out.println("Send message : " + tem);
        }

        //6. 关闭连接
        channel.close();
        connection.close();


    }
}
