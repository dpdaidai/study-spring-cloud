package top.dpdaidai.cloud.study04ribbonconsumer.web;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;
import top.dpdaidai.cloud.study04ribbonconsumer.entity.User;

/**
 * 继承HystrixCommand , 实现UserCommand
 *
 * @Author chenpantao
 * @Date 4/16/21 2:27 PM
 * @Version 1.0
 */
public class S3_UserCommand extends HystrixCommand<User> {

    private RestTemplate restTemplate;

    private Long id;

    public S3_UserCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected User run() throws Exception {
        User user = restTemplate.getForObject("http://HELLO-SERVICE/users/{1}", User.class, id);
        return user;
    }
}
