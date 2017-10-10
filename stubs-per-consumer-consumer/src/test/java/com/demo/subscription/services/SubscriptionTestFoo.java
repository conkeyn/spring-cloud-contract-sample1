package com.demo.subscription.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@AutoConfigureStubRunner(ids = "com.demo:stubs-per-consumer-producer", workOffline = true, stubsPerConsumer = true, consumerName = "foo")
public class SubscriptionTestFoo {

    @Autowired
    StubTrigger trigger;
    @Autowired
    SubscriptionService service;
    @Test
    public void should_increase_the_eligible_counter_when_verification_was_accepted() throws Exception {
        int initialCounter = service.notEligibleCounter.get();
        //指定producer端的契约文件(groovy)中的label值
        trigger.trigger("rejected_verification");
        then(service.notEligibleCounter.get()).isGreaterThan(initialCounter);
    }
}
