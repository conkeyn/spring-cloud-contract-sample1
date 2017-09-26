package com.demo.subscription;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(workOffline = true,ids ="com.demo:message-account-service")
public class SubscriptionServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
