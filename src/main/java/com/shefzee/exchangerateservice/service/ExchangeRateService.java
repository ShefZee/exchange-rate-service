package com.shefzee.exchangerateservice.service;

import com.shefzee.exchangerateservice.request.ExchangeRateRequest;
import com.shefzee.exchangerateservice.response.ExchangeRateResponse;

import java.util.List;

public interface ExchangeRateService {

    ExchangeRateResponse save(ExchangeRateRequest request);

    List<ExchangeRateResponse> findAll();

    ExchangeRateResponse findBySourceAndTarget(String sourceCurrency, String targetCurrency);

}
