package top.dpdaidai.cloud.study16steamhello.config2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

/**
 * @Author chenpantao
 * @Date 5/4/21 6:02 PM
 * @Version 1.0
 */
@Component
public class InputReceiver {

    private static final Logger logger = LoggerFactory.getLogger(InputReceiver.class);

    @ServiceActivator(inputChannel = InputSink.INPUT)
    public void receive(Object payLoad) {
        logger.info("receive : {}", payLoad);
    }


}
