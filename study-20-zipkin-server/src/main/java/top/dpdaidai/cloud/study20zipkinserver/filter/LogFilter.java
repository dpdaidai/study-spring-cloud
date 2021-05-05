package top.dpdaidai.cloud.study20zipkinserver.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author chenpantao
 * @Date 4/14/21 1:38 PM
 * @Version 1.0
 */
@Component
public class LogFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(LogFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //打印收到的请求消息
        HttpServletRequest request1 = (HttpServletRequest) servletRequest;
        if (!request1.getMethod().equalsIgnoreCase("OPTIONS")) {
            logger.info("Request Info:  {}", RequestUtil.requestInfo(request1));
        }

        //对于非OPTIONS类请求记录日志
        //备注OPTIONS类请求有如下特征：
        //无请求体 成功的响应有响应体 安全 幂等性 不可缓存 不允许表单
        //OPTIONS一般用于：检测服务器所支持的请求方法、CORS中的预检请求
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
