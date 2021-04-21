package top.dpdaidai.cloud.study03eurekaclient.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.dpdaidai.cloud.study09serviceapi.entity.User;
import top.dpdaidai.cloud.study09serviceapi.web.HelloService;

/**
 * @Author chenpantao
 * @Date 4/21/21 3:21 PM
 * @Version 1.0
 */
@RestController
public class TestFeignRefactorController implements HelloService {

    @Override
    public String hello() {
        return "hello";
    }

    @Override
    public String param(@RequestParam("name") String name) {
        return name;
    }

    @Override
    public User header(@RequestHeader("id") Long id, @RequestHeader("name") String name) {
        return new User(id, name);
    }

    @Override
    public String body(@RequestBody User user) {
        return user.toString();
    }
}
