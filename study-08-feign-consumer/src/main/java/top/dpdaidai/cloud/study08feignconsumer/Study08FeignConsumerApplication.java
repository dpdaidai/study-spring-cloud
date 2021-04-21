package top.dpdaidai.cloud.study08feignconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
//开启Spring cloud Feign支持
@EnableFeignClients
public class Study08FeignConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Study08FeignConsumerApplication.class, args);
    }

}
