package com.shefzee.exchangerate.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(
        basePackages = {
                "com.shefzee.exchangerate.controller",
                "com.shefzee.exchangerate.error",
                "com.shefzee.exchangerate.helper",
                "com.shefzee.exchangerate.service",
                "com.shefzee.exchangerate.service.impl",
                "com.shefzee.exchangerate.validators",
                "com.shefzee.exchangerate.job"
        }
)
@EnableJpaRepositories(basePackages = {"com.shefzee.exchangerate.repository"})
public class ExchangeRateServiceConfiguration  {
}
