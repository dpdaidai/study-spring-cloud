package top.dpdaidai.cloud.study13apigatewaydynamicroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class Study13ApiGatewayDynamicRouteApplication {

    public static void main(String[] args) {
        SpringApplication.run(Study13ApiGatewayDynamicRouteApplication.class, args);
    }

    // @RefreshScope 将Zuul的配置内容动态化
    @RefreshScope
    @ConfigurationProperties("zuul")
    @Bean
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }


}
