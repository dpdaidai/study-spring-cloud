package top.dpdaidai.cloud.study18trace1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Study18Trace1Application {

    public static void main(String[] args) {
        SpringApplication.run(Study18Trace1Application.class, args);
    }

}
