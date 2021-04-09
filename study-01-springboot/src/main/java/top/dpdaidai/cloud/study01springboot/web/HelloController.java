package top.dpdaidai.cloud.study01springboot.web;

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
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        return "hello boot";
    }
}

