package top.dpdaidai.top.study10apigateway.filter;

import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;

/**
 * 设置Filter再最后执行 ,
 * 捕捉Filter中的异常 , 并写给用户
 *
 * 复用SendErrorFilter的run()逻辑
 *
 * @Author chenpantao
 * @Date 4/23/21 5:06 PM
 * @Version 1.0
 */
@Component
public class ErrorResponseFilter extends SendErrorFilter {
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 30; //大于ErrorFilter的值 , 让它在ErrorFilter后面执行
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

}
