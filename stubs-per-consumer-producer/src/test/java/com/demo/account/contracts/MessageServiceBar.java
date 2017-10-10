package com.demo.account.contracts;

import com.demo.account.domains.PersonToCheck;

import java.util.concurrent.TimeUnit;

public class MessageServiceBar extends MessageServiceBase {
    @Override
    void givenMsg() {
        messageService.shouldGetBeer(new PersonToCheck(25));
    }
}
