package com.shefzee.exchangerate.test;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class MyEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void publish(){
        TestEvent event = new TestEvent(this, UUID.randomUUID().toString());
        System.out.println("Publishing..... " + " - " + Thread.currentThread().getId());
        publisher.publishEvent(event);
        System.out.println("Publishing completed");

    }
}
