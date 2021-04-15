package top.dpdaidai.cloud.study04ribbonconsumer.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author chenpantao
 * @Date 4/15/21 12:42 PM
 * @Version 1.0
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    //断路器 , 当超过hystrix默认的两秒后 , 熔断请求 , 触发回调函数
    //回调函数的参数必须与本方法一致 , 不然会报 FallbackDefinitionException
    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String helloConsumer(HttpServletResponse response) {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://HELLO-SERVICE/testHystrix", String.class);
        HttpHeaders headers = forEntity.getHeaders();
        System.out.println(headers.get("serviceId-host-post").get(0));
        response.addHeader("serviceId-host-post", headers.get("serviceId-host-post").get(0));
        return forEntity.getBody();
    }

    public String helloFallBack(HttpServletResponse response) {
        return "error";
    }


}
