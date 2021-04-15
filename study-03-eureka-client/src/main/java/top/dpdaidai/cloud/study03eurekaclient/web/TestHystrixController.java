package top.dpdaidai.cloud.study03eurekaclient.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chenpantao
 * @Date 4/15/21 12:53 PM
 * @Version 1.0
 */
@RestController
public class TestHystrixController {

    private static final Logger logger = LoggerFactory.getLogger(TestHystrixController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/testHystrix", method = RequestMethod.GET)
    public String testHystrix() {
        return "hello";
    }


}
