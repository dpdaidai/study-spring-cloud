package top.dpdaidai.cloud.study08feignconsumer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.dpdaidai.cloud.study08feignconsumer.entity.User;
import top.dpdaidai.cloud.study08feignconsumer.service.S1_HelloService;

/**
 * @Author chenpantao
 * @Date 4/21/21 12:49 PM
 * @Version 1.0
 */
@RestController
public class S1_ConsumerController {

    @Autowired
    S1_HelloService helloService;

    @RequestMapping(value = "/testFeign", method = RequestMethod.GET)
    public String testFeign() {
        return helloService.ss();
    }

    //测试feign使用 @RequestParam 传参
    @RequestMapping(value = "/param", method = RequestMethod.GET)
    public String param(@RequestParam String name) {
        return helloService.param(name);
    }

    //测试feign使用 @RequestHeader 传参
    @RequestMapping(value = "/header", method = RequestMethod.GET)
    public User header(@RequestHeader Long id, @RequestHeader String name) {
        return helloService.header(id, name);
    }

    //测试feign使用 @RequestBody传参
    @RequestMapping(value = "body", method = RequestMethod.POST)
    public String body(@RequestBody User user) {
        return helloService.body(user);
    }

}
