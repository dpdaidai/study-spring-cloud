package top.dpdaidai.cloud.study08feignconsumer.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 配置Feign客户端的是否打印log
 *
 * @Author chenpantao
 * @Date 4/22/21 2:08 PM
 * @Version 1.0
 */
@Configuration
public class FullLogConfiguration {

    @Bean
    Logger.Level feignLevel(){
        return Logger.Level.FULL;
    }


}
