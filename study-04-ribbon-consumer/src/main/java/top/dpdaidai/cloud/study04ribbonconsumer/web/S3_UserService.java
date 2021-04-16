package top.dpdaidai.cloud.study04ribbonconsumer.web;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import top.dpdaidai.cloud.study04ribbonconsumer.entity.User;

import java.util.Random;
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

    private static final Logger logger = LoggerFactory.getLogger(S3_UserService.class);

    //1  使用HystrixCommand 调用依赖服务client , 这是同步执行
    @HystrixCommand(fallbackMethod = "defaultUser")
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

        //1  设置组名 , 命令名
        //2  默认的线程池划分按照命名分组实现的
        //3  也可以使用HystrixThreadPool来对线程池进行设置 , 建议使用这个参数来指定线程池的划分
        com.netflix.hystrix.HystrixCommand.Setter setter = com.netflix.hystrix.HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("UserCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("UserThreadPoolKey"));
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

    //1  如果defaultUser方法不是一个稳定的逻辑 , 它依然可能会发生异常 , 那么也可以在这个方法上再次指定降级服务
    @HystrixCommand(fallbackMethod = "defaultUserSecond")
    public User defaultUser(Long id, Throwable throwable) {
        logger.info("user service : defaultUser()");

        //超时异常HystrixTimeoutException
        logger.info(throwable.getMessage());
        throwable.printStackTrace();

        User user = new User();
        user.setId(1 / (new Random().nextInt(2) - 1));
        return user;
    }


    //2  除了HystrixBadRequestException之外的异常, 都会被Hystrix认为是命令执行失败, 并触发降级降级服务.
    //3  ignoreExceptions 忽略的异常类型 , 会被包装在HystrixBadRequestException中抛出 , 不再触发后续的fallback逻辑
    //4  defaultUser fallback is a hystrix command. exception: 'class java.lang.ArithmeticException' occurred
    //   in fallback was ignored and wrapped to HystrixBadRequestException.
    //5  向上一层层抛异常后 , 最后返回给用户的就是异常信息 , 需要后台全局捕获后处理
    @HystrixCommand(fallbackMethod = "defaultUserThird", ignoreExceptions = ArithmeticException.class)
    public User defaultUserSecond(Long id, Throwable throwable) {
        logger.info("user service : defaultUserSecond()");

        //defaultUser() 方法会抛出除0异常 , 这里可以打印出来 / by zero
        logger.info(throwable.getMessage());
        throwable.printStackTrace();

        User user = new User();
        user.setId(1 / (new Random().nextInt(2) - 1));
        return user;
    }

    //由于defaultUserSecond()已经忽略了ArithmeticException , 这个方法永远不会被调用
    public User defaultUserThird(Long id, Throwable throwable) {

        logger.info("user service : defaultUserThird()");

        logger.info(throwable.getMessage());
        throwable.printStackTrace();

        return new User();
    }
}
