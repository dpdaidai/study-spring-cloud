package top.dpdaidai.top.study10apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ErrorResponseFilter.class);

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
        //判断 : 仅处理来自post过滤器的异常
        RequestContext requestContext = RequestContext.getCurrentContext();
        ZuulFilter failedFilter = (ZuulFilter) requestContext.get("failed.filter");
        if (failedFilter != null && failedFilter.filterType().equals("post")) {
            logger.info("ErrorResponseFilter 执行");
            return true;
        }
        logger.info("ErrorResponseFilter 未执行");
        return false;
    }

}
