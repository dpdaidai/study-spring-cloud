package top.dpdaidai.cloud.study04ribbonconsumer.web;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixRequestCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(S3_UserCommand.class);

    private RestTemplate restTemplate;

    private Long id;

    //setter 用于配置Command的线程组划分
    public S3_UserCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected User run() throws Exception {
        logger.info("user command : run()");
        User user = restTemplate.getForObject("http://HELLO-SERVICE/users/{1}", User.class, id);
        return user;
    }

    @Override
    protected User getFallback(){
        logger.info("user command : getFallback()");
        return new User();
    }

    //1  Hystrix会判断是否重写了该方法 , 如果重写了 , 则会开启缓存
    //2  开启缓存后 , 每次个请求都要调用下面代码来初始化环境 , 不然会报错
    //3  HystrixRequestContext.initializeContext();
    //4  IllegalStateException: Request caching is not available.
    //    Maybe you need to initialize the HystrixRequestContext?
    //5  而且只在同一用户请求的上下文中，相同依赖服务的返回数据始终保持一致。
    //   在当次请求内对同一个依赖进行重复调用，只会真实调用一次。在当次请求内数据可以保证一致性。
    @Override
    protected String getCacheKey(){
        return String.valueOf(id);
    }

    public void flushCache(Long id) {
        HystrixRequestCache.getInstance(this.commandKey, this.concurrencyStrategy).clear(String.valueOf(id));
    }


}
