package top.dpdaidai.cloud.study03eurekaclient.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.dpdaidai.cloud.study09serviceapi.entity.User;
import top.dpdaidai.cloud.study09serviceapi.web.HelloService;

import java.util.Random;

/**
 * @Author chenpantao
 * @Date 4/21/21 3:21 PM
 * @Version 1.0
 */
@RestController
public class TestFeignRefactorController implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(TestFeignRefactorController.class);

    @Override
    public String hello() throws InterruptedException {
        //设置接口随机阻塞0-2秒 , 当阻塞大于一秒时 , 消费者会触发Hystrix的熔断请求
        int i = new Random().nextInt(2000);
        logger.info("sleepTime:{}", i);
        Thread.sleep(i);
        return "testHystrix";
    }

    @Override
    public String param(@RequestParam("name") String name) {
        return name;
    }

    @Override
    public User header(@RequestHeader("id") Long id, @RequestHeader("name") String name) {
        return new User(id, name);
    }

    @Override
    public String body(@RequestBody User user) {
        return user.toString();
    }
}
