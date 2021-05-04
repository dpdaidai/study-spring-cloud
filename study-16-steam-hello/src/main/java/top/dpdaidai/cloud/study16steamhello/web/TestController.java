package top.dpdaidai.cloud.study16steamhello.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.dpdaidai.cloud.study16steamhello.config.OutputSender;

/**
 * @Author chenpantao
 * @Date 5/4/21 5:31 PM
 * @Version 1.0
 */
@RestController
public class TestController {

    @Autowired
    OutputSender outputSender;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test(String message) {
        outputSender.outPut().send(MessageBuilder.withPayload(message).build());
    }

}
