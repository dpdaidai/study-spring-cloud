package top.dpdaidai.cloud.study15rabbitmqhello.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author chenpantao
 * @Date 4/26/21 9:36 PM
 * @Version 1.0
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue init(){
        return new Queue("queue1");
    }

}
