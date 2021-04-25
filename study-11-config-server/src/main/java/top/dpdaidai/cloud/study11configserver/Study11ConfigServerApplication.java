package top.dpdaidai.cloud.study11configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
//开启SpringCloudConfig服务端功能
@EnableConfigServer
//开启微服务注册
@EnableDiscoveryClient
public class Study11ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Study11ConfigServerApplication.class, args);
    }

}
