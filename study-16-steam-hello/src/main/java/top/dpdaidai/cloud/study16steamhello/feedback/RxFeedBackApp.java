package top.dpdaidai.cloud.study16steamhello.feedback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.rxjava.EnableRxJavaProcessor;
import org.springframework.cloud.stream.annotation.rxjava.RxJavaProcessor;
import org.springframework.context.annotation.Bean;

import java.util.Date;

/**
 * @Author chenpantao
 * @Date 5/4/21 11:00 PM
 * @Version 1.0
 */
@EnableRxJavaProcessor
public class RxFeedBackApp {


    private static final Logger logger = LoggerFactory.getLogger(RxFeedBackApp.class);

    @Bean
    public RxJavaProcessor<Date, String> processor() {
        return inputStream -> inputStream.map(data -> {
            logger.info("Receive : " + data);
            return data;
        }).buffer(5).map(data -> String.valueOf("From input Channel Return -" + data));
    }


}
