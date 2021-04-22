package top.dpdaidai.cloud.study08feignconsumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Primary;
import top.dpdaidai.cloud.study09serviceapi.web.HelloService;

@FeignClient(value = "HELLO-SERVICE", fallback = S2_HelloServiceFallback.class)
//标记为主要service , 但是无法被mock , 解决方法https://github.com/spring-cloud/spring-cloud-netflix/issues/1085
@Primary
public interface S2_HelloService extends HelloService {
}
