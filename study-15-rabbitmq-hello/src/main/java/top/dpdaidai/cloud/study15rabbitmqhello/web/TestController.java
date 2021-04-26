package top.dpdaidai.cloud.study15rabbitmqhello.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.dpdaidai.cloud.study15rabbitmqhello.config.Sender;

/**
 * @Author chenpantao
 * @Date 4/26/21 10:34 PM
 * @Version 1.0
 */
@RestController
public class TestController {

    @Autowired
    Sender sender;

    @RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
    public void sendMessage(String message) {
        sender.send(message);
    }

}
