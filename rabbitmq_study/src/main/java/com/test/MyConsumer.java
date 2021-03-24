package com.test;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @ClassName MyConsumer
 * @Description 消费者
 * @Author liangxp
 * @Date 2021/1/28 11:16
 **/
public class MyConsumer {
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

        //4. 声明一个队列
        /*
           队列声明queueDeclare的参数：第一个参数表示队列名称、第二个参数为是否持久化（true表示是，队列将在服务器重启时生存）
           第三个参数为是否是独占队列（创建者可以使用的私有队列，断开后自动删除）
           第四个参数为当所有消费者客户端连接断开时是否自动删除队列、第五个参数为队列的其他参数
        */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        /*
           true:表示自动确认，只要消息从队列中获取，无论消费者获取到消息后是否成功消费，都会认为消息已经成功消费
           false:表示手动确认，消费者获取消息后，服务器会将该消息标记为不可用状态，等待消费者的反馈，如果消费者一
           直没有反馈，那么该消息将一直处于不可用状态，并且服务器会认为该消费者已经挂掉，不会再给其发送消息，
           直到该消费者反馈。
        */

        //5. 创建消费者并接收消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };

        //6. 设置 Channel 消费者绑定队列
        /*
          basicConsume的第二个参数autoAck: 应答模式
          true：自动应答，即消费者获取到消息，该消息就会从队列中删除掉
          false：手动应答，当从队列中取出消息后，需要程序员手动调用方法应答
          如果没有应答，该消息还会再放进队列中，就会出现该消息一直没有被消费掉的现象
        */
        channel.basicConsume(QUEUE_NAME, true, consumer);

    }
}
