package top.dpdaidai.cloud.study04ribbonconsumer.web;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
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
    public Observable<User> getUserByIdUseUserCommand(Long id) throws ExecutionException, InterruptedException {
        com.netflix.hystrix.HystrixCommand.Setter setter = com.netflix.hystrix.HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("UserCommand"));
        Observable<User> observe = new S3_UserCommand(setter, restTemplate, id).observe();
        return observe;
    }

    //4  使用UserCommand 调用服务 , 这是异步执行
    public Observable<User> getUserByIdUseUserCommandAsync(Long id) throws ExecutionException, InterruptedException {
        com.netflix.hystrix.HystrixCommand.Setter setter = com.netflix.hystrix.HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserGroupAsync"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("UserCommandAsync"));
        Observable<User> observe = new S3_UserCommand(setter, restTemplate, id).toObservable();
        return observe;
    }

    //5
    public Observable<User> getUserByIdA(Long id) {
        com.netflix.hystrix.HystrixObservableCommand.Setter setter = com.netflix.hystrix.HystrixObservableCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("UserCommand"));
        Observable<User> observe = new S3_UserObservableCommand(setter, restTemplate, id).observe();
        return observe;
    }

    public Observable<User> getUserByIdB(Long id) {
        return Observable.create(observer -> {
            try {
                if (!observer.isUnsubscribed()) {
                    User user = restTemplate.getForObject("http://HELLO-SERVICE/users/{1}", User.class, id);
                    observer.onNext(user);
                    observer.onCompleted();
                }
            } catch (Exception e) {
                observer.onError(e);
            }
        });
    }

}
