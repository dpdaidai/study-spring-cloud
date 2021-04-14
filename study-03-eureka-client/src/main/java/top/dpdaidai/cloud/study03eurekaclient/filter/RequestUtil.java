package top.dpdaidai.cloud.study03eurekaclient.filter;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {

    /**
     * 将request中的信息转换到String中
     *
     * @param request 请求
     * @return 自定义表示类型的字符串
     */
    public static String requestInfo(HttpServletRequest request) {
        StringBuilder params = requestParams(request);
        //向接口传递的参数长度大于0时将第一个“&”替换为“？”
        //设置日志的打印格式
        //格式为：“请求方式 请求资源 参数 请求语句”
//        return String.format("%s %s%s qs=%s", request.getMethod(), request.getRequestURI(), params, request.getQueryString());
        return String.format("%s %s%s", request.getMethod(), request.getRequestURI(), params);
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;

    }

    public static StringBuilder requestParams(HttpServletRequest request) {
        StringBuilder params = new StringBuilder();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            if ("password".equalsIgnoreCase(name)) {
                continue;
            }
            String value = request.getParameter(name);
            params.append(String.format("&%s=%s", name, value));
        }
        if (params.length() > 0) {
            params = params.replace(0, 1, "?");
        }
        return params;
    }

    public static Map<String, String> getHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }

}
