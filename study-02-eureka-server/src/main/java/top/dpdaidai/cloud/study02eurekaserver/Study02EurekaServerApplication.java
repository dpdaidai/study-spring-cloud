package top.dpdaidai.cloud.study02eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Study02EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Study02EurekaServerApplication.class, args);
    }

}
