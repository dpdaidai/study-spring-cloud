package top.dpdaidai.cloud.study16steamhello.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @Author chenpantao
 * @Date 5/4/21 5:25 PM
 * @Version 1.0
 */
@EnableBinding(OutputSender.class)
public class OutPutReceiver {

    private static final Logger logger = LoggerFactory.getLogger(OutPutReceiver.class);

    @StreamListener(OutputSender.OUTPUT)
    public void receive(Object payLoad) {
        logger.info("receive output : {}", payLoad);
    }

}
