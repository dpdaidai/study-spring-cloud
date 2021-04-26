package top.dpdaidai.cloud.study14apigatewaydynamicfilter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author chenpantao
 * @Date 4/26/21 5:19 PM
 * @Version 1.0
 */
@ConfigurationProperties("zuul.filter")
public class FilterConfiguration {

    private String directory;
    private Integer interval;

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

}
