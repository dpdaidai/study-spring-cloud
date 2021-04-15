package top.dpdaidai.cloud.study04ribbonconsumer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import top.dpdaidai.cloud.study04ribbonconsumer.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

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
    public String paramArray(Integer id, String name, HttpServletResponse response) {
        ResponseEntity<String> forEntity =
                restTemplate.getForEntity(
                        "http://HELLO-SERVICE/getForEntity/paramArray?id={1}&name={2}", String.class, id, name);
        HttpHeaders headers = forEntity.getHeaders();
        System.out.println(headers.get("serviceId-host-post").get(0));
        response.addHeader("serviceId-host-post", headers.get("serviceId-host-post").get(0));
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

    //使用urlVariables发送POST请求 , 并返回User对象 , 使用该发送发送的对象需要使用@RequestBody接收
    @RequestMapping(value = "/postForEntity/paramArray", method = RequestMethod.GET)
    public User postForEntityTestParamArray(Integer id, String name) {
        User user = new User(id, name);
        ResponseEntity<User> userResponseEntity =
                restTemplate.postForEntity("http://HELLO-SERVICE/postForEntity/paramArray?id={1}&name={2}", user, User.class, id, name);
        return userResponseEntity.getBody();
    }

    //使用Map发送请求 , 并返回Map对象
    @RequestMapping(value = "/postForEntity/paramMap", method = RequestMethod.GET)
    public Map postForEntityTestParamMap(Integer id, String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("name", name);
        ResponseEntity<Map> userResponseEntity =
                restTemplate.postForEntity("http://HELLO-SERVICE/postForEntity/paramMap?id={1}&name={2}", paramMap, Map.class, id, name);
        return userResponseEntity.getBody();
    }

    //使用urlVariables发送POST请求 , 并返回User对象 , 使用该发送发送的对象需要使用@RequestBody接收
    @RequestMapping(value = "/postForObject/paramArray", method = RequestMethod.GET)
    public User postForObjectTestParamArray(Integer id, String name) {
        User user = new User(id, name);
        User user1 = restTemplate.postForObject("http://HELLO-SERVICE/postForObject/paramArray?id={1}&name={2}", user, User.class, id, name);
        return user1;
    }

    //使用postForLocation
    //和前两者的唯一区别在于返回值是一个URI。该URI返回值体现的是：用于提交完成数据之后的页面跳转，或数据提交完成之后的下一步数据操作URI。
    @RequestMapping(value = "/postForLocation/paramArray", method = RequestMethod.GET)
    public String postForLocation(Integer id, String name) {
        User user = new User(id, name);

        //TODO 下面这个方法的返回值是null , 暂时不清楚怎么解决
        URI uri = restTemplate.postForLocation("http://HELLO-SERVICE/postForLocation/paramArray?id={1}&name={2}", user, id, name);
        return uri.toString();
    }

    //使用urlVariables发送PUT请求 , put()方法没有返回值 , 所以也不需要设置返回的类型
    @RequestMapping(value = "/testPut", method = RequestMethod.GET)
    public String testPut(Integer id, String name) {
        User user = new User(id, name);
        restTemplate.put("http://HELLO-SERVICE/put?id={1}&name={2}", user, id, name);
        return "ok";
    }

    //使用urlVariables发送DELETE请求
    //delete()方法没有返回值 , 所以也不需要设置返回的类型
    //delete()方法也不能携带请求对象
    @RequestMapping(value = "/testDelete", method = RequestMethod.GET)
    public String testDelete(Integer id, String name) {
        restTemplate.delete("http://HELLO-SERVICE/delete?id={1}&name={2}", id, name);
        return "ok";
    }


}
