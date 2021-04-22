package top.dpdaidai.cloud.study08feignconsumer.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.dpdaidai.cloud.study09serviceapi.entity.User;

/**
 * 为Feign客户端的定义接口编写一个具体的接口实现类
 * 配置该类为服务降级lei
 * @Author chenpantao
 * @Date 4/22/21 12:40 PM
 * @Version 1.0
 */
//修改路径 , 不然会冲突无法启动
@RequestMapping("/fallback")
@Component
public class S2_HelloServiceFallback implements S2_HelloService {

    @Override
    public String hello() throws InterruptedException {
        return "error hello()";
    }

    @Override
    public String param(@RequestParam("name") String name) {
        return "error param";
    }

    @Override
    public User header(@RequestHeader("id") Long id, @RequestHeader("name") String name) {
        return new User(0L, "error");
    }

    @Override
    public String body(@RequestBody User user) {
        return "error user";
    }
}
