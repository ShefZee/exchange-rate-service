package com.shefzee.exchangerate;

import com.shefzee.exchangerate.config.RootConfiguration;
import com.shefzee.exchangerate.entity.ExchangeRate;
import com.shefzee.exchangerate.repository.ExchangeRateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;
import java.util.Objects;

@SpringBootApplication
@Slf4j
@RootConfiguration
@EnableScheduling
public class Application implements CommandLineRunner {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    private static final Date INITIALIZED_DATETIME = new Date();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean(name = "validationMessageSource")
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages/validation_messages");
        return messageSource;
    }

    @EventListener(ApplicationStartingEvent.class)
    public void appStartingEvent(){
        log.info("Applicaton Starting, " + INITIALIZED_DATETIME);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void appStartedEvent(){
        Date startedDate = new Date();
        log.info("Application Started, " +startedDate + ", time taken "
        + ((startedDate.getTime() - INITIALIZED_DATETIME.getTime()))
                + " ms"
        );
    }

    @Override
    public void run(String... args) throws Exception {
        addExchangeRate("USD","INR",75.0);
        addExchangeRate("AED","INR",20.0);
        addExchangeRate("EUR","USD",1.17);
        addExchangeRate("EUR","JPY",129.0);
    }

    private void addExchangeRate(String sourceCurrency, String targetCurrency, Double value){
        ExchangeRate exchangeRate;
        exchangeRate = exchangeRateRepository.findBySourceCurrencyAndTargetCurrency(sourceCurrency,targetCurrency);
        if(Objects.isNull(exchangeRate)){
            exchangeRate = ExchangeRate.builder()
                    .sourceCurrency(sourceCurrency)
                    .targetCurrency(targetCurrency)
                    .value(value)
                    .build();
            exchangeRateRepository.save(exchangeRate);
            log.info(sourceCurrency + " - " + targetCurrency + " - " + value);
        }
    }
}
