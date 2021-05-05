package top.dpdaidai.cloud.study19trace2.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author chenpantao
 * @Date 5/5/21 6:27 PM
 * @Version 1.0
 */
@RestController
public class TraceController {

    private static final Logger logger = LoggerFactory.getLogger(TraceController.class);

    @RequestMapping(value = "/trace2",method = RequestMethod.GET)
    public String trace(){
        logger.info("=== call trace-2 ===");
        return "trace";
    }



}
