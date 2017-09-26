package com.demo.subscription.services;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SubscriptionService {

    AtomicInteger eligibleCounter = new AtomicInteger();
    AtomicInteger notEligibleCounter = new AtomicInteger();

    @StreamListener(Sink.INPUT)
    public void listen(Verification verification){

        if(verification.eligible){
            eligibleCounter.incrementAndGet();
        }else{
            notEligibleCounter.incrementAndGet();
        }
    }
}
