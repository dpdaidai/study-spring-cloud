package top.dpdaidai.cloud.study12configclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Study12ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(Study12ConfigClientApplication.class, args);
    }

}

