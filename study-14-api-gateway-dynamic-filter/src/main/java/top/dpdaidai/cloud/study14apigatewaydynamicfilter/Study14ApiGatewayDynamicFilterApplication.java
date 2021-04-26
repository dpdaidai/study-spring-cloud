package top.dpdaidai.cloud.study14apigatewaydynamicfilter;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import top.dpdaidai.cloud.study14apigatewaydynamicfilter.config.FilterConfiguration;

@SpringBootApplication
@EnableConfigurationProperties({FilterConfiguration.class})
@EnableZuulProxy
public class Study14ApiGatewayDynamicFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(Study14ApiGatewayDynamicFilterApplication.class, args);
    }

    @Bean
    public FilterLoader filterLoader(FilterConfiguration filterConfiguration) {
        FilterLoader filterLoader = FilterLoader.getInstance();
        filterLoader.setCompiler(new GroovyCompiler());
        try {
            FilterFileManager.setFilenameFilter(new GroovyFileFilter());
            FilterFileManager.init(
                    filterConfiguration.getInterval(),
                    filterConfiguration.getDirectory() + "/pre",
                    filterConfiguration.getDirectory() + "/post");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return filterLoader;
    }
}
