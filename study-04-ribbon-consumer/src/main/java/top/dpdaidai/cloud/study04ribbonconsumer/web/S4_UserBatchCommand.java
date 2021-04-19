package top.dpdaidai.cloud.study04ribbonconsumer.web;

import com.netflix.hystrix.HystrixCommand;
import top.dpdaidai.cloud.study04ribbonconsumer.entity.User;

import java.util.List;

import static com.netflix.hystrix.HystrixCommandGroupKey.Factory.asKey;

/**
 *
 * 准备一个批量请求命令的实现
 *
 * @Author chenpantao
 * @Date 4/19/21 3:07 PM
 * @Version 1.0
 */
public class S4_UserBatchCommand extends HystrixCommand<List<User>> {

    S4_UserService userService;

    List<Long> userIds;

    protected S4_UserBatchCommand(S4_UserService userService, List<Long> userIds) {
        super(Setter.withGroupKey(asKey("userServiceCommand")));
        this.userIds = userIds;
        this.userService = userService;
    }

    @Override
    protected List<User> run() throws Exception {
        return userService.findAll(userIds);
    }

}
