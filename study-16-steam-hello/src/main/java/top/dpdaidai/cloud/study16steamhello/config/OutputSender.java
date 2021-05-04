package top.dpdaidai.cloud.study16steamhello.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Author chenpantao
 * @Date 5/4/21 5:24 PM
 * @Version 1.0
 */
public interface OutputSender {

    String OUTPUT = "output_sender";

    @Output(OutputSender.OUTPUT)
    MessageChannel outPut();

}
