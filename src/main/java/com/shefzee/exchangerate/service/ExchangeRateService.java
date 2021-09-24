package com.shefzee.exchangerate.service;

import com.shefzee.exchangerate.request.ExchangeRateRequest;
import com.shefzee.exchangerate.response.ExchangeRateResponse;

import java.util.List;

public interface ExchangeRateService {

    ExchangeRateResponse save(ExchangeRateRequest request);

    List<ExchangeRateResponse> findAll();

    ExchangeRateResponse findBySourceAndTarget(String sourceCurrency, String targetCurrency);

}
