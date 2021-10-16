package com.shefzee.exchangerate.repository;

import com.shefzee.exchangerate.entity.BaseExchangeRate;
import com.shefzee.exchangerate.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseExchangeRateRepository extends JpaRepository<BaseExchangeRate,String> {

    BaseExchangeRate findBySourceCurrencyAndTargetCurrency(String sourceCurrency, String targetCurrency);

}
