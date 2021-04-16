package top.dpdaidai.cloud.study04ribbonconsumer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.dpdaidai.cloud.study04ribbonconsumer.entity.User;

import java.util.concurrent.ExecutionException;

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
        return userService.getUserByIdUseUserCommand(id);
    }

    @RequestMapping(value = "/getUserByIdUseUserCommandAsync/{id}", method = RequestMethod.GET)
    public User getUserByIdUseUserCommandAsync(@PathVariable Long id) throws ExecutionException, InterruptedException {
        return userService.getUserByIdUseUserCommandAsync(id);
    }
}
