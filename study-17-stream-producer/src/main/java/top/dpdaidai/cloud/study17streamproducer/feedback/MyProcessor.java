package top.dpdaidai.cloud.study17streamproducer.feedback;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyProcessor {

    String INPUT = "my_input";

    String OUTPUT = "my_output";

    @Output(MyProcessor.OUTPUT)
    MessageChannel output();

    @Input(MyProcessor.INPUT)
    SubscribableChannel input();

}
