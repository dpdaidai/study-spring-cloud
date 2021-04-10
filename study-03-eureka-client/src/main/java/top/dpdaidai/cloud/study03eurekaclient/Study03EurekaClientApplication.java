package top.dpdaidai.cloud.study03eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Study03EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(Study03EurekaClientApplication.class, args);
    }

}
