package top.dpdaidai.cloud.study19trace2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Study19Trace2Application {

    public static void main(String[] args) {
        SpringApplication.run(Study19Trace2Application.class, args);
    }

}
