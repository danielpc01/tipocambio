package com.challenge.tipocambio.business.exchange.rate.impl.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ExchangeRateSearchFilterDto {
    private Long currencyTypeOriginId;
    private Long currencyTypeTargetId;
    private String amount;
}
