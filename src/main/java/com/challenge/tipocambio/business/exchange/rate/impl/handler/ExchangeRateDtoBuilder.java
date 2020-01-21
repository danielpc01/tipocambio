package com.challenge.tipocambio.business.exchange.rate.impl.handler;

import com.challenge.tipocambio.business.exchange.rate.impl.model.CurrencyTypeDto;
import com.challenge.tipocambio.business.exchange.rate.impl.model.ExchangeRateDto;
import com.challenge.tipocambio.persistence.model.CurrencyType;
import com.challenge.tipocambio.persistence.model.ExchangeRate;

import java.math.BigDecimal;

public class ExchangeRateDtoBuilder {


    public ExchangeRateDto getBuildExchangeRate(BigDecimal amount,
                                                ExchangeRate exchangeRate,
                                                CurrencyType currencyOrigin,
                                                CurrencyType currencyTarget) {
        return ExchangeRateDto
                .builder()
                .amount(amount)
                .amountWithExchangeRate(amount.multiply(exchangeRate.getAmount()))
                .exchangeRate(exchangeRate.getAmount())
                .currencyOrigin(getBuildCurrencyTypeDto(currencyOrigin))
                .currencyTarget(getBuildCurrencyTypeDto(currencyTarget))
                .build();
    }

    private CurrencyTypeDto getBuildCurrencyTypeDto(CurrencyType currency) {
        return CurrencyTypeDto
                .builder()
                .id(currency.getId())
                .name(currency.getName())
                .symbol(currency.getSymbol())
                .build();
    }
}
