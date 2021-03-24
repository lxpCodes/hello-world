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
public class SenderQueue {
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

        Queue queue = session.createQueue("user");

        // 5.1.消息创建者

        MessageProducer producer = session.createProducer(queue);
        //	producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        // 5.2. 创建文本消息
        for (int i = 0; i < 1; i++) {
            TextMessage textMessage = session.createTextMessage("hi: " + i);
            // 设置延迟发送 重复次数 间隔时间
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,10 * 1000);
            textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT,9);
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD,2 * 1000);
            // 5.3 向目的地写入消息
            if(i % 4 == 0) {
                // 设置消息的优先级
                // 对producer 整体设置
                //	producer.setPriority(9);
                //	producer.send(textMessage,DeliveryMode.PERSISTENT,9,1000 * 100);
                textMessage.setJMSPriority(9);
            }
            producer.send(textMessage);
            //	Thread.sleep(3000);
        }



        // 5.2. 创建序列化对象
        /*Girl girl = new Girl(18,"yanglp");
        ObjectMessage objectMessage = session.createObjectMessage(girl);
        producer.send(objectMessage);

        // 字节流 图片/文件
        BytesMessage bytesMessage = session.createBytesMessage();
        bytesMessage.writeUTF("hello world");
        producer.send(bytesMessage);*/

        /*MapMessage mapMessage = session.createMapMessage();
        mapMessage.setString("name","qiuxiang");
        mapMessage.setInt("age",18);
        mapMessage.setDouble("price",1800.00);
        // 当ttl到期，进入死信队列，保证有一定时效性消息
        producer.setTimeToLive(2000);
        producer.send(mapMessage);*/


        // 6.关闭连接
        connection.close();

        System.out.println("System exit....");


    }
}
