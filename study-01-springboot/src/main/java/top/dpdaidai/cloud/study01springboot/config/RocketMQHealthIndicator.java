package top.dpdaidai.cloud.study01springboot.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @Author chenpantao
 * @Date 4/10/21 2:50 PM
 * @Version 1.0
 */
@Component
public class RocketMQHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        int errorCode = check();
        if (errorCode != 0) {
            return Health.down().withDetail("Error Code", errorCode).build();
        }

        return Health.up().build();

    }

    private int check(){
        //对监控对象得检测操作
        //..

        return 0;
    }
}
