package top.dpdaidai.cloud.study04ribbonconsumer.web;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.observables.BlockingObservable;
import top.dpdaidai.cloud.study04ribbonconsumer.entity.User;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author chenpantao
 * @Date 4/16/21 2:39 PM
 * @Version 1.0
 */
@Service
public class S3_UserService {

    @Autowired
    RestTemplate restTemplate;


    //1  使用HystrixCommand 调用依赖服务client , 这是同步执行
    @HystrixCommand
    public User getUserById(Long id) {
        User user = restTemplate.getForObject("http://HELLO-SERVICE/users/{1}", User.class, id);
        return user;
    }

    //2 使用HystrixCommand 调用依赖服务client , 这是异步执行
    @HystrixCommand
    public Future<User> getUserByIdAsync(Long id) {
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                User user = restTemplate.getForObject("http://HELLO-SERVICE/users/{1}", User.class, id);
                return user;
            }
        };
    }

    //3 使用UserCommand 调用服务 , 这是同步执行
    public User getUserByIdUseUserCommand(Long id) throws ExecutionException, InterruptedException {
        com.netflix.hystrix.HystrixCommand.Setter setter = com.netflix.hystrix.HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("UserCommand"));
        Observable<User> observe = new S3_UserCommand(setter, restTemplate, id).observe();
        BlockingObservable<User> userBlockingObservable = observe.toBlocking();
        Future<User> userFuture = userBlockingObservable.toFuture();
        User user = userFuture.get();
        return user;
    }

    //4  使用UserCommand 调用服务 , 这是异步执行
    public User getUserByIdUseUserCommandAsync(Long id) throws ExecutionException, InterruptedException {
        com.netflix.hystrix.HystrixCommand.Setter setter = com.netflix.hystrix.HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("UserCommand"));
        Observable<User> observe = new S3_UserCommand(setter, restTemplate, id).toObservable();
        BlockingObservable<User> userBlockingObservable = observe.toBlocking();
        Future<User> userFuture = userBlockingObservable.toFuture();
        User user = userFuture.get();
        return user;
    }


}
