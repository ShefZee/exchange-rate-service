package com.shefzee.exchangerateservice.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(
        basePackages = {
                "com.shefzee.exchangerateservice.controller",
                "com.shefzee.exchangerateservice.error",
                "com.shefzee.exchangerateservice.helper",
                "com.shefzee.exchangerateservice.service",
                "com.shefzee.exchangerateservice.service.impl",
                "com.shefzee.exchangerateservice.validators"
        }
)
@EnableJpaRepositories(basePackages = {"com.shefzee.exchangerateservice.repository"})
public class ExchangeRateServiceConfiguration  {
}
