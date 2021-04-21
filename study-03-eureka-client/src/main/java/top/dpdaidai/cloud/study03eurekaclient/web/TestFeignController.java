package top.dpdaidai.cloud.study03eurekaclient.web;

import org.springframework.web.bind.annotation.*;
import top.dpdaidai.cloud.study03eurekaclient.entity.User;

/**
 * @Author chenpantao
 * @Date 4/21/21 1:59 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/feign")
public class TestFeignController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        return "hello";
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
