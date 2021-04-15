package top.dpdaidai.cloud.study04ribbonconsumer.config;

import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author chenpantao
 * @Date 4/14/21 5:10 PM
 * @Version 1.0
 */

/**
 * TODO
 * 为spring添加一个IPing替换默认NoOpPing的实例检查策略
 * 但是会报 VIP address for client null is null
 *
 * -----
 *
 * 似乎是由于Eureka和Ribbon同时使用 , 会触发Eureka中实现的对Ribbon的自动化配置
 * 只修改部分配置会导致其余Ribbon的配置不兼容
 *
 */

@Configuration
public class MyRibbonConfiguration {

//    @Bean
//    public IClientConfig iClientConfig() {
//        return new DefaultClientConfigImpl();
//    }
//
//    @Bean
//    public IPing ribbonPing(IClientConfig iClientConfig) {
//        return new PingUrl();
//    }


}
