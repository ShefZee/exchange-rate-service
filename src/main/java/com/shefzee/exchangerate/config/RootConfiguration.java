package com.shefzee.exchangerate.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ExchangeRateServiceConfiguration.class,
        })
public @interface RootConfiguration {
}
