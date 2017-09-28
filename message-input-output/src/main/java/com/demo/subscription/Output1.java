package com.demo.subscription;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Output1 {
    String OUTPUT = "output1";

    @Output("output1")
    MessageChannel output1();
}