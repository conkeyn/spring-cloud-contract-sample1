package com.demo.subscription.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(ids = "com.demo:message-account-service:+:stubs", workOffline = true)
public class SubscriptionTest {

    @Autowired
    private StubTrigger trigger;

    @Autowired
    private SubscriptionService service;

    @Test
    public void should_increase_the_eligible_counter_when_verification_was_accepted() throws Exception {
        int initialCounter = service.eligibleCounter.get();
        //指定producer端的契约文件(groovy)中的label值
        trigger.trigger("accepted_verification");

        then(service.eligibleCounter.get()).isGreaterThan(initialCounter);
    }
}
