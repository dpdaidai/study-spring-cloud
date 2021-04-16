package top.dpdaidai.cloud.study03eurekaclient.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import top.dpdaidai.cloud.study03eurekaclient.entity.User;

import java.util.Random;

/**
 * @Author chenpantao
 * @Date 4/15/21 12:53 PM
 * @Version 1.0
 */
@RestController
public class TestHystrixController {

    private static final Logger logger = LoggerFactory.getLogger(TestHystrixController.class);

    @RequestMapping(value = "/testHystrix", method = RequestMethod.GET)
    public String testHystrix() throws InterruptedException {

        //设置接口随机阻塞0-2秒 , 当阻塞大于一秒时 , 消费者会触发Hystrix的熔断请求
        int i = new Random().nextInt(2000);
        logger.info("sleepTime:{}", i);
        Thread.sleep(i);
        return "testHystrix";
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User users(@PathVariable int id) throws InterruptedException {

        //设置接口随机阻塞0-2秒 , 当阻塞大于一秒时 , 消费者会触发Hystrix的熔断请求
        int i = new Random().nextInt(2000);
        logger.info("sleepTime:{}", i);
        Thread.sleep(i);

        User user = new User();
        user.setId(id);
        user.setName("cpt");
        return user;
    }

    @RequestMapping(value = "/postUser", method = RequestMethod.POST)
    public User postUser(@RequestBody User user) {
        return user;
    }


}
