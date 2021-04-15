package top.dpdaidai.cloud.study04ribbonconsumer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author chenpantao
 * @Date 4/15/21 12:48 PM
 * @Version 1.0
 */
@RestController
public class TestHystrixController {

    @Autowired
    HelloService helloService;


    @RequestMapping(value = "/testHystrix", method = RequestMethod.GET)
    public String testHystrix(HttpServletResponse response) {
        String s = helloService.helloConsumer(response);
        return s;
    }


}
