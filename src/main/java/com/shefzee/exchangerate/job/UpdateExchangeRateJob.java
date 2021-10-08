package com.shefzee.exchangerate.job;

import com.shefzee.exchangerate.entity.ExchangeRate;
import com.shefzee.exchangerate.repository.ExchangeRateRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
@ConditionalOnExpression("${cron.enabled:true}")
@Data
public class UpdateExchangeRateJob {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;


    @Scheduled(cron = "${cron.time}")
    public void updateRates(){
        List<ExchangeRate> rates =  exchangeRateRepository.findAll();
        for(ExchangeRate a : rates){
            double rate = a.getValue();
            double decimal = getRandomDecimal(a.getValue(), a.getValue()+ 1) - a.getValue();
            if(new Random().nextBoolean()){
                rate = rate + decimal;
            }else{
                rate = rate - decimal;
            }
            if(rate < 0){
                rate = rate * -1;
            }
            a.setValue(rate);
            a =  exchangeRateRepository.save(a);
            System.out.println(new Date() + " : " + a.getSourceCurrency() + " - " + a.getTargetCurrency() + " = " + a.getValue() );
        }
       /* exchangeRateRepository.findAll()
                .forEach(a -> {
                    double rate = a.getValue();
                    double decimal = getRandomDecimal(a.getValue(), a.getValue()+ 1);
                    if(new Random().nextBoolean()){
                        rate = rate + decimal;
                    }else{
                        rate = rate - decimal;
                    }
                    a.setValue(rate);
                    a =  exchangeRateRepository.save(a);
                    System.out.println(new Date() + " : " + a.getSourceCurrency() + " - " + a.getTargetCurrency() + " = " + a.getValue() );
                });*/
    }

    double getRandomDecimal(double min, double max) {
        Random r = new Random();
        return (r.nextInt((int)((max-min)*10+1))+min*10) / 10.0;
    }
}
