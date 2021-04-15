package top.dpdaidai.cloud.study03eurekaclient.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


}