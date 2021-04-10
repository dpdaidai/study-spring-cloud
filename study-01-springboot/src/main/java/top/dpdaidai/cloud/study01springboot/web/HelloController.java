package top.dpdaidai.cloud.study01springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chenpantao
 * @Date 4/9/21 10:57 PM
 * @Version 1.0
 */
@RestController
public class HelloController {

    @Qualifier("counterService")
    @Autowired
    CounterService counterService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {

        //自定义统计指标信息
        counterService.increment("top.dpdaidai.hello.count");

        return "hello boot";
    }
}

