package top.dpdaidai.cloud.study08feignconsumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import top.dpdaidai.cloud.study09serviceapi.web.HelloService;

@FeignClient(value = "hello-service")
public interface S2_HelloService extends HelloService {
}
