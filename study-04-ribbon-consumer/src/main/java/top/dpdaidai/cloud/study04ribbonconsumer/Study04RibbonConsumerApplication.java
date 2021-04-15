package top.dpdaidai.cloud.study04ribbonconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//开启服务注册
@EnableDiscoveryClient
//开启断路器
@EnableCircuitBreaker
public class Study04RibbonConsumerApplication {

    @Bean
    @LoadBalanced  //开启客户端负载均衡
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Study04RibbonConsumerApplication.class, args);
    }

}
