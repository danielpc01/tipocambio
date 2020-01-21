package com.challenge.tipocambio.business.exchange.rate.impl.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CurrencyTypeDto {

    private Long id;
    private String name;
    private String symbol;
}
