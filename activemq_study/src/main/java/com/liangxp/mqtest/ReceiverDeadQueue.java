package com.liangxp.mqtest;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ReceiverQueue
 * @Description 测试activemq接收
 * @Author liangxp
 * @Date 2020/12/15 10:08
 **/
public class ReceiverDeadQueue {

    public static void main(String[] args) throws JMSException {
        // 1.获取连接工厂


        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "admin",
                "admin",
                "tcp://10.21.7.98:61616"
        );

        // 添加信任的持久化类型
        List<String> list = new ArrayList<>();
        list.add(Girl.class.getPackage().getName());
        connectionFactory.setTrustedPackages(list);

        // 2.获取一个向ActiveMQ的连接
        Connection connection = connectionFactory.createConnection();

        connection.start();
        // 3.获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4. 找目的地，获取destination，消费端，也会从这个目的地取消息

        Destination deadQueue = session.createQueue("ActiveMQ.DLQ");
        MessageConsumer consumer1 = session.createConsumer(deadQueue);
        consumer1.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (message instanceof MapMessage){
                    MapMessage mapMessage = (MapMessage) message;
                    try {
                        System.out.println("ActiveMQ.DLQ message:" + mapMessage);
                        System.out.println("ActiveMQ.DLQ message:name=" + mapMessage.getString("name"));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


//        Destination queue = session.createQueue("user");

        // 5.获取消息

//        MessageConsumer consumer = session.createConsumer(queue);
//        consumer.setMessageListener(new MyListener());


    }
}
