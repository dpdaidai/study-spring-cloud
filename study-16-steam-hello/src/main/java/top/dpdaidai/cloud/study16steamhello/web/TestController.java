package top.dpdaidai.cloud.study16steamhello.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.dpdaidai.cloud.study16steamhello.config.OutputSender;
import top.dpdaidai.cloud.study16steamhello.config.User;

/**
 * @Author chenpantao
 * @Date 5/4/21 5:31 PM
 * @Version 1.0
 */
@RestController
public class TestController {

    @Autowired
    OutputSender outputSender;

    @Autowired
    @Qualifier(OutputSender.OUTPUT)
    MessageChannel output;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test(String name, Long id) {
        User user = new User(id, name);
        outputSender.outPut().send(MessageBuilder.withPayload(user).build());
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public void test2(String name, Long id) {
        User user = new User(id, name);
        output.send(MessageBuilder.withPayload(user).build());
    }

    @RequestMapping(value = "/testObject", method = RequestMethod.GET)
    public void testObject(String name, Long id) {
        User user = new User(id, name);
        output.send(MessageBuilder.withPayload(user).build());
    }
}
