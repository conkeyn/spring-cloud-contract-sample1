package com.demo.subscription.services;

import com.demo.subscription.Output1;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

@Service
public class SubscriptionService {
    AtomicInteger eligibleCounter = new AtomicInteger();
    AtomicInteger notEligibleCounter = new AtomicInteger();

    Output1 output1;

    public SubscriptionService( Output1 output1){
        this.output1 = output1;
    }

    @StreamListener(Sink.INPUT)
    public void listen(Verification verification) {
        if (verification.eligible) {
            eligibleCounter.incrementAndGet();
        } else {
            notEligibleCounter.incrementAndGet();
        }

        //发送到新的队列上
        output1.output1().send(MessageBuilder.withPayload(
                new MiddleServiceMessage(verification.eligible, eligibleCounter.get(),
                        notEligibleCounter.get(), "tranform from middle serivce.")).build());
    }

    static class Verification implements Serializable {
        public boolean eligible;
    }


    static class MiddleServiceMessage implements Serializable {
        public boolean eligible;
        public String content;
        public int receivedCount = 0;
        public int rejectedCount = 0;

        public MiddleServiceMessage() {
        }

        public MiddleServiceMessage(boolean eligible, int receivedCount, int rejectedCount,
                String content) {
            this.eligible = eligible;
            this.receivedCount = receivedCount;
            this.rejectedCount = rejectedCount;
            this.content = content;
        }
    }
}

