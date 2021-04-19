package top.dpdaidai.cloud.study04ribbonconsumer.web;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.dpdaidai.cloud.study04ribbonconsumer.entity.User;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @Author chenpantao
 * @Date 4/19/21 2:04 PM
 * @Version 1.0
 */
@RestController
public class S4_UserController {

    @Autowired
    S4_UserService userService;

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public User find(Long id) {
        return userService.find(id);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<User> findAll(String ids) {
        List<Long> collect = Arrays.stream(StringUtils.split(ids, ","))
                .map(Long::valueOf).collect(Collectors.toList());
        List<User> all = userService.findAll(collect);
        return all;
    }

    @RequestMapping(value = "/useCollapseCommand", method = RequestMethod.GET)
    public void useCollapseCommand(String ids) throws ExecutionException, InterruptedException {

        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        S4_UserCollapseCommand s4_userCollapseCommand = new S4_UserCollapseCommand(userService, 1L);
        S4_UserCollapseCommand s4_userCollapseCommand1 = new S4_UserCollapseCommand(userService, 2L);
        S4_UserCollapseCommand s4_userCollapseCommand2 = new S4_UserCollapseCommand(userService, 3L);
        S4_UserCollapseCommand s4_userCollapseCommand3 = new S4_UserCollapseCommand(userService, 4L);

        Future<User> queue = s4_userCollapseCommand.queue();
        Future<User> queue1 = s4_userCollapseCommand1.queue();
        Future<User> queue2 = s4_userCollapseCommand2.queue();
        Thread.sleep(300);
        Future<User> queue3 = s4_userCollapseCommand3.queue();

//                Request Info:  GET /useCollapseCommand?ids=1
//                ----------start-----------
//                user : User{id=1, name='cpt'}
//                user : User{id=2, name='cpt'}
//                user : User{id=3, name='cpt'}
//                -----------end----------
//                ----------start-----------
//                user : User{id=4, name='cpt'}
//                -----------end----------
    }

    @RequestMapping(value = "/useCollapseCommandByAnnotation", method = RequestMethod.GET)
    public void useCollapseCommandByAnnotation(String ids) throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<User> byAnnotation = userService.findByAnnotation(1L);
        Future<User> byAnnotation1 = userService.findByAnnotation(2L);
        Future<User> byAnnotation2 = userService.findByAnnotation(3L);
        Thread.sleep(300);
        Future<User> byAnnotation3 = userService.findByAnnotation(4L);

        User s = byAnnotation.get();
//        System.out.println(byAnnotation);
//        System.out.println(byAnnotation1);
//        System.out.println(byAnnotation2);
//        System.out.println(byAnnotation3);

    }

}
