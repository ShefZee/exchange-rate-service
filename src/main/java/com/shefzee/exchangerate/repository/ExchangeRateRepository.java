package com.shefzee.exchangerate.repository;

import com.shefzee.exchangerate.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate,String> {

    ExchangeRate findBySourceCurrencyAndTargetCurrency(String sourceCurrency,String targetCurrency);

}
