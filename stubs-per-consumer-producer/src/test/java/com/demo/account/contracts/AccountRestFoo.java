package com.demo.account.contracts;

import com.demo.account.domains.Account;

import org.mockito.BDDMockito;

public class AccountRestFoo extends AccountRestBase{
    @Override
    void givenService() {
        BDDMockito.given(accountService.getById(BDDMockito.argThat(isIneger()))).willReturn(new Account("1","friends","aaaa@126.com"));
    }
}
