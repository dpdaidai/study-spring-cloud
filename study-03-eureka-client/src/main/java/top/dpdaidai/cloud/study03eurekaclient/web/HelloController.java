package top.dpdaidai.cloud.study03eurekaclient.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.dpdaidai.cloud.study03eurekaclient.entity.User;

import java.util.Map;

/**
 * @Author chenpantao
 * @Date 4/10/21 4:16 PM
 * @Version 1.0
 */
@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        return "hello";
    }

    @RequestMapping(value = "/getForEntity/paramArray", method = RequestMethod.GET)
    public String paramArray(Integer id, String name) {
        return id + "," + name;
    }

    @RequestMapping(value = "/getForEntity/paramMap", method = RequestMethod.GET)
    public String paramMap(Integer id, String name) {
        return id + "," + name;
    }

    @RequestMapping(value = "/getForEntity/paramURI", method = RequestMethod.GET)
    public String paramURI(Integer id, String name) {
        return id + "," + name;
    }

    @RequestMapping(value = "/getForObject", method = RequestMethod.GET)
    public String getForObject(Integer id, String name) {
        return id + "," + name;
    }

    @RequestMapping(value = "/postForEntity/paramArray", method = RequestMethod.POST)
    public User postForEntityTestParamArray(int id, String name, @RequestBody User user) {
        logger.info(user.toString());
        logger.info(id + "," + name);
        return user;
    }

    @RequestMapping(value = "/postForEntity/paramMap", method = RequestMethod.POST)
    public Map postForEntityTestParamMap(int id, String name, @RequestBody Map map) {
        logger.info(map.toString());
        logger.info(id + "," + name);
        return map;
    }

    @RequestMapping(value = "/postForObject/paramArray", method = RequestMethod.POST)
    public User postForObjectTestParamArray(int id, String name, @RequestBody User user) {
        logger.info(user.toString());
        logger.info(id + "," + name);
        return user;
    }

    @RequestMapping(value = "/postForLocation/paramArray", method = RequestMethod.POST)
    public User postForLocationTestParamArray(int id, String name, @RequestBody User user) {
        logger.info(user.toString());
        logger.info(id + "," + name);
        return user;
    }


    @RequestMapping(value = "/put", method = RequestMethod.PUT)
    public User put(int id, String name, @RequestBody User user) {
        logger.info(user.toString());
        logger.info(id + "," + name);
        return user;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(int id, String name) {
        logger.info(id + "," + name);
        return id + "," + name;
    }
}
