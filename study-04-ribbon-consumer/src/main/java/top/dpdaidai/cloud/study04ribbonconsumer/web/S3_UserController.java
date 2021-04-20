package top.dpdaidai.cloud.study04ribbonconsumer.web;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;
import rx.observables.BlockingObservable;
import top.dpdaidai.cloud.study04ribbonconsumer.entity.User;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author chenpantao
 * @Date 4/16/21 3:31 PM
 * @Version 1.0
 */
@RestController
public class S3_UserController {

    @Autowired
    S3_UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(S3_UserController.class);

    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/getUserByIdAsync/{id}", method = RequestMethod.GET)
    public User getUserByIdAsync(@PathVariable Long id) throws ExecutionException, InterruptedException {
        User user = userService.getUserByIdAsync(id).get();
        return user;
    }

    @RequestMapping(value = "/getUserByIdUseUserCommand/{id}", method = RequestMethod.GET)
    public User getUserByIdUseUserCommand(@PathVariable Long id) throws ExecutionException, InterruptedException {
        HystrixRequestContext.initializeContext();
        Observable<User> observe = userService.getUserByIdUseUserCommand(id);

        BlockingObservable<User> userBlockingObservable = observe.toBlocking();
        Future<User> userFuture = userBlockingObservable.toFuture();
        User user = userFuture.get();
        return user;
    }

    @RequestMapping(value = "/getUserByIdUseUserCommandAsync/{id}", method = RequestMethod.GET)
    public User getUserByIdUseUserCommandAsync(@PathVariable Long id) throws ExecutionException, InterruptedException {
        HystrixRequestContext.initializeContext();

        Observable<User> observe = userService.getUserByIdUseUserCommandAsync(id);

        BlockingObservable<User> userBlockingObservable = observe.toBlocking();
        Future<User> userFuture = userBlockingObservable.toFuture();
        User user = userFuture.get();
        return user;
    }

    @RequestMapping(value = "/getUserByIdA/{id}", method = RequestMethod.GET)
    public User getUserByIdA(@PathVariable Long id) throws ExecutionException, InterruptedException {
        Observable<User> observe = userService.getUserByIdA(id);

        BlockingObservable<User> userBlockingObservable = observe.toBlocking();
        Future<User> userFuture = userBlockingObservable.toFuture();
        User user = userFuture.get();
        return user;
    }

    @RequestMapping(value = "/getUserByIdB/{id}", method = RequestMethod.GET)
    public User getUserByIdB(@PathVariable Long id) throws ExecutionException, InterruptedException {
        Observable<User> observe = userService.getUserByIdB(id);

        BlockingObservable<User> userBlockingObservable = observe.toBlocking();
        Future<User> userFuture = userBlockingObservable.toFuture();
        User user = userFuture.get();
        return user;
    }

    @RequestMapping(value = "/testFallback/{id}", method = RequestMethod.GET)
    public User testFallback(@PathVariable Long id) {
        return userService.testFallback(id);
    }

    @RequestMapping(value = "/testCache/{id}", method = RequestMethod.GET)
    public User testCache(@PathVariable Long id) throws ExecutionException, InterruptedException {

        //初始化 HystrixRequestContext
        HystrixRequestContext.initializeContext();

        //第一次查询
        Observable<User> observe = userService.testCache(id);
        BlockingObservable<User> userBlockingObservable = observe.toBlocking();
        Future<User> userFuture = userBlockingObservable.toFuture();
        User user = userFuture.get();
        logger.info("第一次查询 : {}", user);


        //第二次查询
        Observable<User> observe1 = userService.testCache(id);
        BlockingObservable<User> userBlockingObservable1 = observe1.toBlocking();
        Future<User> userFuture1 = userBlockingObservable1.toFuture();
        User user1 = userFuture1.get();
        logger.info("第二次查询 : {}", user1);

        //清除缓存
        userService.flushCache(id);

        //第三次请求
        Observable<User> observe2 = userService.testCache(id);
        BlockingObservable<User> userBlockingObservable2 = observe2.toBlocking();
        Future<User> userFuture2 = userBlockingObservable2.toFuture();
        User user2 = userFuture2.get();
        logger.info("第三次查询 : {}", user2);

        return user;

    }

    //使用注解的方式开启和清除缓存
    //https://www.cnblogs.com/hellxz/p/9056806.html
    //三种方式 :
    //1  使用getCacheKey方法获取cacheKey
    //2  使用@CacheKey指定cacheKey
    //3  使用默认所有参数作为cacheKey
    @RequestMapping(value = "/testCacheByAnnotation/{id}", method = RequestMethod.GET)
    public User testCacheByAnnotation(@PathVariable Long id) {
        HystrixRequestContext.initializeContext();
        //第一次查询
        User userById = userService.testCacheByAnnotation(id);
        logger.info("第一次查询 : {}", userById);

        //第二次查询
        User userById1 = userService.testCacheByAnnotation(id);
        logger.info("第二次查询 : {}", userById1);

        //移出缓存
        userService.postUser(userById);
        logger.info("清理缓存 : {}", userById);

        //第三次查询
        User userById2 = userService.testCacheByAnnotation(id);
        logger.info("第三次查询 : {}", userById2);

        return userById2;

    }


}
