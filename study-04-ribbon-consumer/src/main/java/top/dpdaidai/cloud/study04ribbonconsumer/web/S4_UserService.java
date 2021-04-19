package top.dpdaidai.cloud.study04ribbonconsumer.web;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.dpdaidai.cloud.study04ribbonconsumer.entity.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author chenpantao
 * @Date 4/19/21 2:01 PM
 * @Version 1.0
 */
@Service
public class S4_UserService {

    @Autowired
    RestTemplate restTemplate;

    public User find(Long id) {
        User user = restTemplate.getForObject("http://HELLO-SERVICE/users/{1}", User.class, id);
        return user;
    }

    public List<User> findAll(List<Long> ids) {

        //这个方法返回List的时候 , 因为不清楚List内部的对象是什么 , 所有会包装成List<LinkedHashMap>
        //但是这样居然不会报错就返回了....
//        List forObject = restTemplate.getForObject("http://HELLO-SERVICE/users?ids={1}", List.class,
//                StringUtils.join(ids, ","));
        User[] forObject = restTemplate.getForObject("http://HELLO-SERVICE/users?ids={1}", User[].class,
                StringUtils.join(ids, ","));

        return Arrays.stream(forObject).collect(Collectors.toList());
    }


}
