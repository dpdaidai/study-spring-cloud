//package top.dpdaidai.cloud.study08feignconsumer.config;
//
//import feign.Feign;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//
///**
// * https://blog.csdn.net/lvyuan1234/article/details/77155919
// * 就算你在@FeignClient注解中没有引用该配置类，
// * 他也会被启动类上的注解扫描到(参见@SpringBootApplication注解中的@ComponentScan注解）
// * 所以这是一个全局关闭熔断的配置 , 不应该使用
// *
// * @Author chenpantao
// * @Date 4/22/21 11:54 AM
// * @Version 1.0
// */
//@Configuration
//public class DisableHystrixConfiguration {
//
//    @Scope("prototype")
//    public static Feign.Builder feignBuilder() {
//        return Feign.builder();
//    }
//
//}
