package com.liangxp.mqtest;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;

/**
 * @ClassName SenderQueue
 * @Description 测试activemq发送
 * @Author liangxp
 * @Date 2020/12/15 10:07
 **/
public class SenderTopic {
    public static void main(String[] args) throws JMSException {

        // 1.获取连接工厂


        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "admin",
                "admin",
                "tcp://10.21.7.98:61616"
        );
        // 2.获取一个向ActiveMQ的连接
        Connection connection = connectionFactory.createConnection();
        // 3.获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4. 找目的地，获取destination，消费端，也会从这个目的地取消息

        Destination queue = session.createTopic("test_topic");

        // 51.消息创建者

        MessageProducer producer = session.createProducer(queue);
        //	producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // 5.2. 创建消息
        TextMessage textMessage = session.createTextMessage("hi: world");



        // 5.3 向目的地写入消息
        // 设置消息的优先级
        // 对producer 整体设置
        //	producer.setPriority(9);
        //	producer.send(textMessage,DeliveryMode.PERSISTENT,9,1000 * 100);
//        textMessage.setJMSPriority(9);

        producer.send(textMessage);

        // 6.关闭连接
        connection.close();

        System.out.println("System exit....");


    }
}
