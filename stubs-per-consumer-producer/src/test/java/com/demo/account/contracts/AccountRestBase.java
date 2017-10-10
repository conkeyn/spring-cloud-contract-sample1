package com.demo.account.contracts;

import com.demo.account.controllers.AccountController;
import com.demo.account.services.AccountService;

import org.codehaus.plexus.util.StringUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(MockitoJUnitRunner.class)
public abstract class AccountRestBase {

    @Mock
    AccountService accountService;

    @InjectMocks
    AccountController accountController;

    @Before
    public void setup() {
        givenService();
        RestAssuredMockMvc.standaloneSetup(accountController);
    }

    TypeSafeMatcher<String> isIneger() {
        return new TypeSafeMatcher<String>() {
            @Override
            protected boolean matchesSafely(String item) {
                return StringUtils.isNumeric(item);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    abstract void givenService();



}
