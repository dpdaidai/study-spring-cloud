package top.dpdaidai.cloud.study08feignconsumer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chenpantao
 * @Date 4/21/21 12:49 PM
 * @Version 1.0
 */
@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/testFeign",method = RequestMethod.GET)
    public String testFeign(){
        return helloService.hello();
    }


}
