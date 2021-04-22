package top.dpdaidai.cloud.study03eurekaclient.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import top.dpdaidai.cloud.study03eurekaclient.entity.User;

import java.util.Random;

/**
 * @Author chenpantao
 * @Date 4/21/21 1:59 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/feign")
public class TestFeignController {

    private static final Logger logger = LoggerFactory.getLogger(TestFeignController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() throws InterruptedException {
        //设置接口随机阻塞0-2秒 , 当阻塞大于一秒时 , 消费者会触发Hystrix的熔断请求
        int i = new Random().nextInt(2000);
        logger.info("sleepTime:{}", i);
        Thread.sleep(i);
        return "testHystrix";
    }

    @RequestMapping(value = "/param", method = RequestMethod.GET)
    public String param(@RequestParam String name) {
        return name;
    }

    @RequestMapping(value = "/header", method = RequestMethod.GET)
    public User header(@RequestHeader Long id, @RequestHeader String name) {
        return new User(id, name);
    }

    @RequestMapping(value = "body", method = RequestMethod.POST)
    public String body(@RequestBody User user) {
        return user.toString();
    }

}
