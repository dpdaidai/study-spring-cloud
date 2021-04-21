package top.dpdaidai.cloud.study09serviceapi.web;

import org.springframework.web.bind.annotation.*;
import top.dpdaidai.cloud.study09serviceapi.entity.User;

/**
 * @Author chenpantao
 * @Date 4/21/21 12:45 PM
 * @Version 1.0
 */
//该注解标记本service对应的服务实例
@RequestMapping("/refactor")
public interface HelloService {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String hello();

    @RequestMapping(value = "/param", method = RequestMethod.GET)
    String param(@RequestParam("name") String name);

    @RequestMapping(value = "/header", method = RequestMethod.GET)
    User header(@RequestHeader("id") Long id, @RequestHeader("name") String name);

    @RequestMapping(value = "/body", method = RequestMethod.POST)
    String body(@RequestBody User user);


}
