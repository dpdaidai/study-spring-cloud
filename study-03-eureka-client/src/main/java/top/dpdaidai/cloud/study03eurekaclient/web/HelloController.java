package top.dpdaidai.cloud.study03eurekaclient.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chenpantao
 * @Date 4/10/21 4:16 PM
 * @Version 1.0
 */
@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        ServiceInstance localServiceInstance = discoveryClient.getLocalServiceInstance();
        logger.info("/hello , host : {} , service_id : {}", localServiceInstance.getHost(), localServiceInstance.getServiceId());
        return "hello";
    }

    @RequestMapping(value = "/getForEntity/paramArray", method = RequestMethod.GET)
    public String paramArray(Integer id, String name) {
        ServiceInstance localServiceInstance = discoveryClient.getLocalServiceInstance();
        logger.info("/getForEntity/paramArray , host : {} , service_id : {}", localServiceInstance.getHost(), localServiceInstance.getServiceId());
        return id + "," + name;
    }

    @RequestMapping(value = "/getForEntity/paramMap", method = RequestMethod.GET)
    public String paramMap(Integer id, String name) {
        ServiceInstance localServiceInstance = discoveryClient.getLocalServiceInstance();
        logger.info("/getForEntity/paramMap , host : {} , service_id : {}", localServiceInstance.getHost(), localServiceInstance.getServiceId());
        return id + "," + name;
    }

    @RequestMapping(value = "/getForEntity/paramURI", method = RequestMethod.GET)
    public String paramURI(Integer id, String name) {
        ServiceInstance localServiceInstance = discoveryClient.getLocalServiceInstance();
        logger.info("/getForEntity/paramURI , host : {} , service_id : {}", localServiceInstance.getHost(), localServiceInstance.getServiceId());
        return id + "," + name;
    }

    @RequestMapping(value = "/getForObject", method = RequestMethod.GET)
    public String getForObject(Integer id, String name) {
        ServiceInstance localServiceInstance = discoveryClient.getLocalServiceInstance();
        logger.info("/getForObject , host : {} , service_id : {}", localServiceInstance.getHost(), localServiceInstance.getServiceId());
        return id + "," + name;
    }

}
