package com.demo.subscription.contracts;

import com.demo.subscription.SubscriptionServiceApplication;
import com.demo.subscription.services.SubscriptionService;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubTrigger;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SubscriptionServiceApplication.class)
@AutoConfigureMessageVerifier()
@AutoConfigureStubRunner(ids = "com.demo:message-account-service:+:stubs", workOffline = true)
public class ContractVerifierBase {
    @Inject
    private MessageVerifier verifier;

    @Autowired
    private StubTrigger trigger;

    @Autowired
    private SubscriptionService service;


    @Before
    public void  setup(){
        verifier.receive("output1",100, TimeUnit.MILLISECONDS);
    }


    public void triggeMessageFromSourceStub(){
        trigger.trigger("accepted_verification");
    }
}
