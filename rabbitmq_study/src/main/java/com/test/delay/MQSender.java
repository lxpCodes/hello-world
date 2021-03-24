package com.test.delay;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Date;

/**
 * @ClassName MQSender
 * @Description TODO
 * @Author liangxp
 * @Date 2021/3/8 14:59
 **/
@Component
public class MQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //confirmCallback returnCallback 代码省略，请参照上一篇

    public void sendLazy(Object message){
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((data, ack, cause) -> {
            if (!ack) {
                System.out.println("消息发送失败!" + cause + data.toString());
            } else {
                System.out.println("消息发送成功,消息ID：" + (data != null ? data.getId() : null));
            }
        });
        rabbitTemplate.setReturnCallback((message2, replyCode, replyText,
                                          exchange, routingKey) ->
                System.out.println(MessageFormat.format("消息发送ReturnCallback:{0},{1},{2},{3},{4},{5}", 2, replyCode, replyText, exchange, routingKey)));
        //id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData("12345678909"+new Date());

        //发送消息时指定 header 延迟时间
        rabbitTemplate.convertAndSend(MQConfig.LAZY_EXCHANGE, "lazy.boot", message,
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        //设置消息持久化
                        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        //message.getMessageProperties().setHeader("x-delay", "6000");
                        message.getMessageProperties().setDelay(6000);
                        return message;
                    }
                }, correlationData);
    }
}
