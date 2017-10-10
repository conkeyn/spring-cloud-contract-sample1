package com.demo.account.services;

import com.demo.account.domains.PersonToCheck;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageService {

    Source source;

    public MessageService(Source source){
        this.source = source;
    }

    public Boolean shouldGetBeer(PersonToCheck personToCheck) {
        //remove::start[]
        //tag::impl[]
        boolean shouldGetBeer = personToCheck.age >= 20;
        source.output().send(MessageBuilder.withPayload(new Verification(shouldGetBeer)).build());
        return shouldGetBeer;
        //end::impl[]
        //remove::end[return]
    }

    public static class Verification {
        boolean eligible;

        public Verification(boolean eligible) {
            this.eligible = eligible;
        }

        public Verification() {
        }

        public boolean isEligible() {
            return eligible;
        }

        public void setEligible(boolean eligible) {
            this.eligible = eligible;
        }
    }
}
