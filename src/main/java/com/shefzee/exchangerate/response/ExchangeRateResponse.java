package com.shefzee.exchangerate.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExchangeRateResponse implements Response {

    private String id;
    private String sourceCurrency;
    private String targetCurrency;
    private String value;
}
