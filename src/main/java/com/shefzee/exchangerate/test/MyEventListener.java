package com.shefzee.exchangerate.test;

import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@EnableAsync
@Configuration
public class MyEventListener  {

    @SneakyThrows
    @Async
    @EventListener
    public void listener1(TestEvent event) {
        Thread.sleep(5000);
        System.out.println("got message - "+ event.getId() + " - " + Thread.currentThread().getId());
    }
}
