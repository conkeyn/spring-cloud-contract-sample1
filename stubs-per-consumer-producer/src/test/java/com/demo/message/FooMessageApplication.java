package com.demo.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableBinding({Source.class})
@ComponentScans({@ComponentScan(basePackageClasses = com.demo.account.services.MessageService.class)})
public class FooMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(FooMessageApplication.class, args);
    }
}
