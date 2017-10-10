package com.demo.account.contracts;

import com.demo.account.controllers.LoginLogController;
import com.demo.account.services.LoginLogService;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(MockitoJUnitRunner.class)
public class LoginLogRestBase {

    @Mock
    LoginLogService loginLogService;

    @InjectMocks
    LoginLogController loginLogController;

    @Before
    public void setup() {
        BDDMockito.given(loginLogService.findLogList(BDDMockito.argThat(dateBeforeYear2000()),
                BDDMockito.argThat(dateBeforeYear2000()))).willReturn(buildLogList());
        RestAssuredMockMvc.standaloneSetup(loginLogController);
    }

    private TypeSafeMatcher<Date> dateBeforeYear2000() {
        return new TypeSafeMatcher<Date>() {
            @Override
            protected boolean matchesSafely(Date item) {
                return item != null && item.before(new Date(2000, 0, 1));
            }
            @Override
            public void describeTo(Description description) {
            }
        };
    }

    public  List<String> buildLogList() {
        List<String> logList = new LinkedList<>();
        logList.add("log 1");
        logList.add("log 2");
        logList.add("log 3");
        logList.add("log 4");
        logList.add("log 5");
        return logList;
    }

}
