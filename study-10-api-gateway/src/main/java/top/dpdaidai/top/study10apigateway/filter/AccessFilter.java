package top.dpdaidai.top.study10apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author chenpantao
 * @Date 4/22/21 4:29 PM
 * @Version 1.0
 */
@Component
public class AccessFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    // 过滤器类型 , 决定过滤器在哪个生命周期运行 . pre代表路由之前执行
    @Override
    public String filterType() {
        return "pre";
    }

    //过滤器的执行优先顺序
    @Override
    public int filterOrder() {
        return 0;
    }

    //判断该过滤器是不否需要被执行.
    //true代表所有请求生效
    //实际运用过程中利用该函数指定过滤器有效范围
    @Override
    public boolean shouldFilter() {
        return true;
    }

    //过滤器的具体逻辑
    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        logger.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

        Object accessToken = request.getParameter("accessToken");
        //验证不通过 , 设置返回码
        if(accessToken == null) {
            logger.warn("access token is empty");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            return null;
        }
        logger.info("access token ok");
        return null;
    }
}
