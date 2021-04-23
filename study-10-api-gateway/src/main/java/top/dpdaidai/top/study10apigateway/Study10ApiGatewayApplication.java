package top.dpdaidai.top.study10apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//开启zuul的api网关功能
@EnableZuulProxy
public class Study10ApiGatewayApplication {

    /**
     * 这个bean会将按照 serviceId-v1 这样的命名规则的service ,
     * 来创建/v1/serviceId/**的路由匹配规则
     * 例如 feign-consumer-v1 ---> /v1/feign-consumer/**
     * 此配置在关闭自动创建路由规则的时候是不生效的
     * @return
     */
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper(
                "(?<name>^.+)-(?<version>v.+$)",
                "${version}/${name}");
    }


    public static void main(String[] args) {
        SpringApplication.run(Study10ApiGatewayApplication.class, args);
    }

}
