package com.liangxp.mqtest;

import javax.jms.*;

/**
 * @ClassName MyListener
 * @Description 监听器自定义实现
 * @Author liangxp
 * @Date 2020/12/15 14:17
 **/
public class MyListener implements MessageListener {
    @Override
    public void onMessage(Message message) {

        if (message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println(textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else if (message instanceof ObjectMessage){
            ObjectMessage objectMessage = (ObjectMessage) message;
            try {
                // 强转为对应的对象
                Girl girl = (Girl) objectMessage.getObject();
                System.out.println(girl.toString());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else if (message instanceof BytesMessage){
            BytesMessage bytesMessage = (BytesMessage) message;
            try {
                String utf = bytesMessage.readUTF();
                System.out.println(utf);
            } catch (JMSException e) {
                e.printStackTrace();
            }

        } else if(message instanceof MapMessage){
            MapMessage mapMessage = (MapMessage) message;
            try {
                System.out.println(mapMessage);
                System.out.println(mapMessage.getString("name"));
                System.out.println(mapMessage.getInt("age"));
                System.out.println(mapMessage.getDouble("price"));
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }
}
