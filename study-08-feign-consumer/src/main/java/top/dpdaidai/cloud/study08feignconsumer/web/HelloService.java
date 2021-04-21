package top.dpdaidai.cloud.study08feignconsumer.web;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author chenpantao
 * @Date 4/21/21 12:45 PM
 * @Version 1.0
 */
//该注解标记本service对应的服务实例
@FeignClient("hello-service")
public interface HelloService {

    @RequestMapping("/hello")
    String hello();

}
