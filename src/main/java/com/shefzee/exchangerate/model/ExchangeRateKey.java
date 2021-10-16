package com.shefzee.exchangerate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Data
public class ExchangeRateKey {

    private String source;
    private String target;
}
