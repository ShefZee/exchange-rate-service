package com.shefzee.exchangerateservice.repository;

import com.shefzee.exchangerateservice.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate,String> {

    ExchangeRate findBySourceCurrencyAndTargetCurrency(String sourceCurrency,String targetCurrency);

}
