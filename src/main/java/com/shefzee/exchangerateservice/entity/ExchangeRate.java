package com.shefzee.exchangerateservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRate extends BaseEntity {


    @Column
    private String sourceCurrency;

    @Column
    private String targetCurrency;

    @Column
    private Double value;
}
