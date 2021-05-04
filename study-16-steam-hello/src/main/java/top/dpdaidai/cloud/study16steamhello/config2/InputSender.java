package top.dpdaidai.cloud.study16steamhello.config2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author chenpantao
 * @Date 5/4/21 6:07 PM
 * @Version 1.0
 */
@Component
public class InputSender {

    private static final Logger logger = LoggerFactory.getLogger(InputSender.class);

    @Bean
    @InboundChannelAdapter(value = InputSink.INPUT, poller = @Poller(fixedDelay = "2000"))
    public MessageSource<Date> timerMessageSource() {
        return () -> new GenericMessage<>(new Date());
    }

    @Transformer(inputChannel = InputSink.INPUT, outputChannel = InputSink.INPUT)
    public Object transform(Date message) {
        return new SimpleDateFormat(("yyyy-MM-dd HH:mm:ss")).format(message);
    }
}
