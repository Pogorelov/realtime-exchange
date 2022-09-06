package com.pogorelov.realtime.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeCurrency {

    private Integer currencyCodeA;
    private Integer currencyCodeB;
    private Long date;
    private Double rateSell;

}
