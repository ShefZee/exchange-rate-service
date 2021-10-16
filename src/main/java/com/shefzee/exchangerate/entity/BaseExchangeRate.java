package com.shefzee.exchangerate.entity;

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
public class BaseExchangeRate extends BaseEntity {


    @Column
    private String sourceCurrency;

    @Column
    private String targetCurrency;

    @Column
    private Double value;
}
