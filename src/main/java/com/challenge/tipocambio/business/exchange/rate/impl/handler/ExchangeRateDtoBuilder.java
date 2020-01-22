package com.challenge.tipocambio.business.exchange.rate.impl.handler;

import com.challenge.tipocambio.business.exchange.rate.impl.model.CurrencyTypeDto;
import com.challenge.tipocambio.business.exchange.rate.impl.model.ExchangeRateDto;
import com.challenge.tipocambio.persistence.model.CurrencyType;
import com.challenge.tipocambio.persistence.model.ExchangeRate;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExchangeRateDtoBuilder {


    private static final int SCALE = 4;

    public ExchangeRateDto getBuildExchangeRate(BigDecimal amount,
                                                ExchangeRate exchangeRate,
                                                CurrencyType currencyOrigin,
                                                CurrencyType currencyTarget) {
        BigDecimal divide = amount.divide(exchangeRate.getAmount(), SCALE, BigDecimal.ROUND_DOWN);
        return ExchangeRateDto
                .builder()
                .amount(amount)
                .amountWithExchangeRate(divide)
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
