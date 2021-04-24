package top.dpdaidai.cloud.study12configclient.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * config client服务根据配置的服务名, 分支 , 环境 , 从配置中心获取更为详细的properties
 *
 * @Author chenpantao
 * @Date 4/24/21 7:22 PM
 * @Version 1.0
 */
@RefreshScope
@RestController
public class TestController {

    @Value("${from}")
    private String from;

    @Autowired
    private Environment environment;

    @RequestMapping("/from")
    public String from() {
        return this.from;
    }

    @RequestMapping("/env")
    public String env(){
        return environment.toString();
    }

}
