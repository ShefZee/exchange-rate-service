package com.shefzee.exchangerateservice.service.impl;

import com.shefzee.exchangerateservice.entity.ExchangeRate;
import com.shefzee.exchangerateservice.repository.ExchangeRateRepository;
import com.shefzee.exchangerateservice.request.ExchangeRateRequest;
import com.shefzee.exchangerateservice.response.ExchangeRateResponse;
import com.shefzee.exchangerateservice.service.ExchangeRateService;
import com.shefzee.exchangerateservice.validators.ExchangeRateBusinessRules;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateBusinessRules exchangeRateBusinessRules;

    @Override
    public ExchangeRateResponse save(ExchangeRateRequest request) {
        //validate
        exchangeRateBusinessRules.validate(request);

        ExchangeRate exchangeRate =  convertTo(request);
        //Check whether record already exist
        ExchangeRate existingRecord = exchangeRateRepository.findBySourceCurrencyAndTargetCurrency(request.getSourceCurrency(),request.getTargetCurrency());
        if(Objects.nonNull(existingRecord)){
            exchangeRate.setId(existingRecord.getId());
        }
         exchangeRate = exchangeRateRepository.save(exchangeRate);
        return convertTo(exchangeRate);
    }

    @Override
    public List<ExchangeRateResponse> findAll() {
        return ListUtils.emptyIfNull(exchangeRateRepository.findAll())
                .stream()
                .map(this::convertTo)
                .collect(Collectors.toList());
    }

    @Override
    public ExchangeRateResponse findBySourceAndTarget(String sourceCurrency, String targetCurrency) {
        return Optional.ofNullable(exchangeRateRepository.findBySourceCurrencyAndTargetCurrency(sourceCurrency,targetCurrency))
                .map(this::convertTo)
                .orElse(null);
    }

    private ExchangeRate convertTo(ExchangeRateRequest exchangeRateRequest){
        return ExchangeRate.builder()
                .sourceCurrency(exchangeRateRequest.getSourceCurrency())
                .targetCurrency(exchangeRateRequest.getTargetCurrency())
                .value(Double.parseDouble(exchangeRateRequest.getValue()))
                .build();
    }

    private ExchangeRateResponse convertTo(ExchangeRate exchangeRate){
        return ExchangeRateResponse.builder()
                .id(exchangeRate.getId())
                .sourceCurrency(exchangeRate.getSourceCurrency())
                .targetCurrency(exchangeRate.getTargetCurrency())
                .value(String.valueOf(exchangeRate.getValue()))
                .build();
    }
}
