package top.dpdaidai.cloud.study05hystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class Study05HystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(Study05HystrixDashboardApplication.class, args);
    }

}
