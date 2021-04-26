package top.dpdaidai.cloud.study15rabbitmqhello.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * 发送mq消息
 *
 * @Author chenpantao
 * @Date 4/26/21 9:30 PM
 * @Version 1.0
 */
@Component
public class Sender {

    //AmqpTemplate定义了一套针对AMQP协议的基础操作 , spring boot 会根据配置来注入其具体实现
    @Autowired
    AmqpTemplate rabbitTemplate;

    public void send(String message){
        System.out.println("send message : " + message);
        rabbitTemplate.convertAndSend("queue1", message);
    }


}
