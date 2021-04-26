package top.dpdaidai.cloud.study15rabbitmqhello.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *
 * 消息消费者
 *
 * @Author chenpantao
 * @Date 4/26/21 9:33 PM
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "queue1")
public class Receiver {

    @RabbitHandler
    public void process(String message) {
        System.out.println("reveive message : " + message);
    }

}
