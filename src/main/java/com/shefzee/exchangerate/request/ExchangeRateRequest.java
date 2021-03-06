package com.shefzee.exchangerate.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ExchangeRateRequest implements Request{

    private String sourceCurrency;
    private String targetCurrency;
    private String value;

}
