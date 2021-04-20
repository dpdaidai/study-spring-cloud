package top.dpdaidai.cloud.study06turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
//开启turbine
@EnableTurbine
@EnableDiscoveryClient
public class Study06TurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(Study06TurbineApplication.class, args);
    }

}
