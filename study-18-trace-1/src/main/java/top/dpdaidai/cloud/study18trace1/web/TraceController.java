package top.dpdaidai.cloud.study18trace1.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author chenpantao
 * @Date 5/5/21 6:21 PM
 * @Version 1.0
 */
@RestController
public class TraceController {

    private static final Logger logger = LoggerFactory.getLogger(TraceController.class);

    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping(value = "/trace1", method = RequestMethod.GET)
    public String trace(HttpServletRequest request) {
        logger.info("=== call trace-1 , Trace id = {} , SpanId = {} , " +
                        "parentId={} ===",
                request.getHeader("X-B3-TraceId"), request.getHeader("X-B3-SpanId"),
                request.getHeader("X-B3-ParentSpanId"));
        return restTemplate.getForEntity("http://TRACE-2/trace2", String.class).getBody();
    }

}
