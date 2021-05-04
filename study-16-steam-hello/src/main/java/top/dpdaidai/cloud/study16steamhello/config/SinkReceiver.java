package top.dpdaidai.cloud.study16steamhello.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @Author chenpantao
 * @Date 5/4/21 4:15 PM
 * @Version 1.0
 */
//该注解用来指定一个或者多个定义了@Input @OutPut 注解的接口 , 以此实现对消息通道Channel的绑定
//@EnableBinding(Sink.class)
public class SinkReceiver {

    private static final Logger logger = LoggerFactory.getLogger(SinkReceiver.class);

    //该注解将被修饰的方法注册为消息中间件上数据流的事件监听器 , 属性值为监听的消息通道名
//    @StreamListener(Sink.INPUT)
//    public void receive(Object payLoad) {
//        logger.info("receive : {}", payLoad);
//    }


}
