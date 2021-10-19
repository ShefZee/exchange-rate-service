package com.shefzee.exchangerate.job;

import com.shefzee.exchangerate.entity.BaseExchangeRate;
import com.shefzee.exchangerate.entity.ExchangeRate;
import com.shefzee.exchangerate.repository.BaseExchangeRateRepository;
import com.shefzee.exchangerate.repository.ExchangeRateRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Component
@Data
public class UpdateExchangeRateJob {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private BaseExchangeRateRepository baseExchangeRateRepository;

    @Value("${log.enabled}")
    private boolean logEnabled;
    @Scheduled(cron = "${cron.time}")
    public void updateRates(){
        try {
            List<ExchangeRate> rates =  exchangeRateRepository.findAll();
            for(ExchangeRate a : rates){
                //get Base exchange rate
                BaseExchangeRate baseExchangeRate = baseExchangeRateRepository.findBySourceCurrencyAndTargetCurrency(a.getSourceCurrency(),a.getTargetCurrency());
                double rate = a.getValue();
                double decimal = getRandomDecimal(a.getValue(), a.getValue()+ 1) - a.getValue();
                if(new Random().nextBoolean()){
                    rate = rate + decimal;
                }else{
                    rate = rate - decimal;
                }
                if(Objects.nonNull(baseExchangeRate)){
                    if(rate < baseExchangeRate.getValue()-3){
                        rate = rate + decimal;
                    }else if(rate > baseExchangeRate.getValue() + 3){
                        rate = rate - decimal;
                    }
                }
                if(rate < 0){
                    rate = rate * -1;
                }
                a.setValue(rate);
                a =  exchangeRateRepository.save(a);
                if (logEnabled) {
                    System.out.println(new Date() + " : " + a.getSourceCurrency() + " - " + a.getTargetCurrency() + " = " + a.getValue() );
                }
            }
        } catch (Exception e) {

        }

    }

    double getRandomDecimal(double min, double max) {
        Random r = new Random();
        return (r.nextInt((int)((max-min)*10+1))+min*10) / 10.0;
    }
}
