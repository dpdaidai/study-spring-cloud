package top.dpdaidai.cloud.study17streamproducer.feedback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import java.util.Date;

/**
 * @Author chenpantao
 * @Date 5/4/21 10:19 PM
 * @Version 1.0
 */
@EnableBinding(value = {MyProcessor.class})
public class ProducerApp {

    private static final Logger logger = LoggerFactory.getLogger(ProducerApp.class);

    @Bean
    @InboundChannelAdapter(value = MyProcessor.OUTPUT, poller = @Poller(fixedDelay = "2000"))
    public MessageSource<Date> timerMessageSource() {
        return () -> new GenericMessage<>(new Date());
    }

    @StreamListener(MyProcessor.INPUT)
    public void receive(Object payLoad) {
        logger.info("receive " + MyProcessor.INPUT + " : {}", payLoad);
    }

}
