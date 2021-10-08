package com.shefzee.exchangerate.test;

import org.springframework.context.ApplicationEvent;


public class TestEvent extends ApplicationEvent {

    private String id;
    public TestEvent(Object source, String id) {
        super(source);
        this.id = id;

    }

    public String getId() {
        return id;
    }
}
