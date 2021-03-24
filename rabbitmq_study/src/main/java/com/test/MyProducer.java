package com.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @ClassName MyProducer
 * @Description 生产者
 * @Author liangxp
 * @Date 2021/1/28 11:15
 **/
public class MyProducer {
    private static final String QUEUE_NAME = "ITEM_QUEUE";

    public static void main(String[] args) throws Exception {
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

        //实际场景中，消息多为json格式的对象
        String msg = "hello";
        //4. 发送三条数据
        for (int i = 1; i <= 3 ; i++) {
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            System.out.println("Send message" + i +" : " + msg);
        }

        //5. 关闭连接
        channel.close();
        connection.close();
    }
}
