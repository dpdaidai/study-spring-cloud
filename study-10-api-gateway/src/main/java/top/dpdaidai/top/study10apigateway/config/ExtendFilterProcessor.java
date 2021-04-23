package top.dpdaidai.top.study10apigateway.config;

import com.netflix.zuul.FilterProcessor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @Author chenpantao
 * @Date 4/23/21 5:23 PM
 * @Version 1.0
 */
public class ExtendFilterProcessor extends FilterProcessor {

    @Override
    public Object processZuulFilter(ZuulFilter zuulFilter) throws ZuulException {
        try {
            return super.processZuulFilter(zuulFilter);
        } catch (ZuulException e) {
            RequestContext requestContext = RequestContext.getCurrentContext();
            requestContext.set("failed.filter", zuulFilter);
            throw e;
        }

    }

}
