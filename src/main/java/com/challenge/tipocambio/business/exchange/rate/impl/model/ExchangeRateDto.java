package com.challenge.tipocambio.business.exchange.rate.impl.model;

import com.challenge.tipocambio.persistence.model.CurrencyType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class ExchangeRateDto {

    private BigDecimal amount;
    private BigDecimal amountWithExchangeRate;
    private BigDecimal exchangeRate;

    private CurrencyTypeDto currencyOrigin;
    private CurrencyTypeDto currencyTarget;

}
