package com.demo.account.contracts;

import com.demo.account.AccountServiceApplication;
import com.demo.account.domains.PersonToCheck;
import com.demo.account.services.MessageService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifier;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountServiceApplication.class)
@AutoConfigureMessageVerifier()
public class ContractVerifierBase {

    @Inject
    private MessageVerifier verifier;

    @Autowired
    private MessageService messageService;

    @Before
    public void  setup(){
        verifier.receive("output",100, TimeUnit.MILLISECONDS);
    }


    public void clientIsOldEnough() {
        //remove::start[]
        messageService.shouldGetBeer(new PersonToCheck(25));
        //remove::end[]
    }
}
