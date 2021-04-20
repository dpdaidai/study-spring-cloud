package top.dpdaidai.cloud.study07turbineamqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@SpringBootApplication
//开启turbine stream流
@EnableTurbineStream
@EnableDiscoveryClient
public class Study07TurbineAmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(Study07TurbineAmqpApplication.class, args);
    }

}
