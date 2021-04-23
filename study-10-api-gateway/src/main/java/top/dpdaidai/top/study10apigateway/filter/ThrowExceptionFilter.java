package top.dpdaidai.top.study10apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * @Author chenpantao
 * @Date 4/23/21 3:26 PM
 * @Version 1.0
 */
@Component
public class ThrowExceptionFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(ThrowExceptionFilter.class);

    //post阶段的异常能够被errorFilter捕获 , 但是却无法再被包装后返回
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        logger.info("This is a pre filter, it will throw a RuntimeException()");
        RequestContext requestContext = RequestContext.getCurrentContext();
        //主动捕捉异常然后设置响应码 ,
        //但是依然会执行后续请求操作 , 如果请求报错 , 该响应码会被覆盖
//        try {
            doWrongThing();
//        } catch (Exception e) {
//            requestContext.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            requestContext.set("error.exception", e);
//        }
        return null;
    }

    private void doWrongThing() {
        throw new RuntimeException("exist some errors...");
    }
}
