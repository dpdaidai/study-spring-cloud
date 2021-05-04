package top.dpdaidai.cloud.study16steamhello.config2;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface InputSink {

    String INPUT = "input_sink";

    @Output(InputSink.INPUT)
    MessageChannel outPut();
}
