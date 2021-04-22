package top.dpdaidai.top.study10apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//开启zuul的api网关功能
@EnableZuulProxy
public class Study10ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(Study10ApiGatewayApplication.class, args);
    }

}
