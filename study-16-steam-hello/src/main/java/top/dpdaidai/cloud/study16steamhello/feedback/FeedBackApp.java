package top.dpdaidai.cloud.study16steamhello.feedback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;


/**
 *
 * 接收消息 , 并使用@SendTo注解反馈消息
 *
 * @Author chenpantao
 * @Date 5/4/21 10:04 PM
 * @Version 1.0
 */

@EnableBinding(value = {MyProcessor.class})
public class FeedBackApp {

    private static final Logger logger = LoggerFactory.getLogger(FeedBackApp.class);

    @StreamListener(MyProcessor.INPUT)
    @SendTo(MyProcessor.OUTPUT)
    public Object receive(Object payLoad) {
        logger.info("receive my_input : {}", payLoad);
        return "From " + MyProcessor.INPUT + " channel : return - " + MyProcessor.OUTPUT;
    }

}
