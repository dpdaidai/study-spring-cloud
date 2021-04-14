package top.dpdaidai.cloud.study04ribbonconsumer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;

/**
 * @Author chenpantao
 * @Date 4/10/21 11:23 PM
 * @Version 1.0
 */
@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
    public String helloConsumer() {
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
    }

    //使用urlVariables发送GET请求
    @RequestMapping(value = "/getForEntity/paramArray", method = RequestMethod.GET)
    public String paramArray(Integer id, String name) {
        ResponseEntity<String> forEntity =
                restTemplate.getForEntity(
                        "http://HELLO-SERVICE/getForEntity/paramArray?id={1}&name={2}", String.class, id, name);
        return forEntity.getBody();
    }

    //使用Map设置参数
    @RequestMapping(value = "/getForEntity/paramMap", method = RequestMethod.GET)
    public String paramMap(Integer id, String name) {

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("name", name);
        //参数设置为Map<Object,Object>时 , 无法重载到这个方法
        ResponseEntity<String> forEntity =
                restTemplate.getForEntity(
                        "http://HELLO-SERVICE/getForEntity/paramMap?id={id}&name={name}", String.class, paramMap);
        return forEntity.getBody();
    }

    //使用URI设置参数
    @RequestMapping(value = "/getForEntity/paramURI", method = RequestMethod.GET)
    public String URI(Integer id, String name) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://HELLO-SERVICE/getForEntity/paramURI?id={1}&name={2}")
                .build().expand(id, name).encode();
        URI uri = uriComponents.toUri();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(uri, String.class);
        return forEntity.getBody();
    }

    //使用getForObject可以省略 ResponseEntity.getBody()步骤直接获取对象
    //该方法可以同时使用上面的三种参数类型
    @RequestMapping(value = "/getForObject", method = RequestMethod.GET)
    public String getForObject(Integer id, String name) {
        String forObject = restTemplate.getForObject(
                "http://HELLO-SERVICE/getForObject?id={1}&name={2}", String.class, id, name);
        return forObject;
    }

}
