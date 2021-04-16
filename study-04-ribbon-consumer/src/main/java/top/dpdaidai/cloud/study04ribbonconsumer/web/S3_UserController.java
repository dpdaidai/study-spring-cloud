package top.dpdaidai.cloud.study04ribbonconsumer.web;

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
        Observable<User> observe = userService.getUserByIdUseUserCommand(id);

        BlockingObservable<User> userBlockingObservable = observe.toBlocking();
        Future<User> userFuture = userBlockingObservable.toFuture();
        User user = userFuture.get();
        return user;
    }

    @RequestMapping(value = "/getUserByIdUseUserCommandAsync/{id}", method = RequestMethod.GET)
    public User getUserByIdUseUserCommandAsync(@PathVariable Long id) throws ExecutionException, InterruptedException {
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
}
