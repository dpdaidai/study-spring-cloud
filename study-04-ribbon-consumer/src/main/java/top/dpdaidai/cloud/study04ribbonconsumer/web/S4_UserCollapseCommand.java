package top.dpdaidai.cloud.study04ribbonconsumer.web;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.dpdaidai.cloud.study04ribbonconsumer.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.netflix.hystrix.HystrixCollapserKey.Factory.asKey;

/**
 *
 * 请求合并器
 *
 * @Author chenpantao
 * @Date 4/19/21 3:12 PM
 * @Version 1.0
 */
public class S4_UserCollapseCommand extends HystrixCollapser<List<User>, User, Long> {

    S4_UserService userService;

    Long userId;

    private static final Logger logger = LoggerFactory.getLogger(S4_UserCollapseCommand.class);

    protected S4_UserCollapseCommand(S4_UserService userService, Long userId) {
        super(Setter.withCollapserKey(asKey("userCollapseCommand"))
                .andCollapserPropertiesDefaults(
                        //设置合并器时间窗
                        HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(100)));
        this.userId = userId;
        this.userService = userService;
    }

    @Override
    public Long getRequestArgument() {
        return userId;
    }

    //将时间窗内的所有请求 , 挨个取出参数 , 然后合并在一起 , 使用userBatchCommand发送一次请求
    @Override
    protected HystrixCommand<List<User>> createCommand(Collection<CollapsedRequest<User, Long>> collapsedRequests) {
        List<Long> userIds = new ArrayList<>(collapsedRequests.size());
        userIds.addAll(collapsedRequests.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList()));
        return new S4_UserBatchCommand(userService, userIds);
    }

    //将userBatchCommand返回的结果 , 挨个映射到原request
    //这里按照id的顺序将结果返回 . 如果id有重复的 , 而服务方将id去重后返回 , 结果可能会有问题 , 使用map包装User会更方便
    @Override
    protected void mapResponseToRequests(List<User> responseUserList, Collection<CollapsedRequest<User, Long>> collapsedRequests) {

        logger.info("----------start-----------");
        for (User user : responseUserList) {
            logger.info("user : {}", user);
        }
        logger.info("-----------end----------");

        int count = 0;
        for (CollapsedRequest<User, Long> collapsedRequest : collapsedRequests) {
            User user = responseUserList.get(count++);
            collapsedRequest.setResponse(user);
        }
    }
}
