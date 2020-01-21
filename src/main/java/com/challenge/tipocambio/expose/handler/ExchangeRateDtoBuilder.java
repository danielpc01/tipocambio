package com.challenge.tipocambio.expose.handler;

import com.challenge.tipocambio.business.exchange.rate.SavedExchangeRateDto;
import com.challenge.tipocambio.business.exchange.rate.impl.model.ExchangeRateSearchFilterDto;
import com.challenge.tipocambio.expose.model.ExchangeRateRequestDto;
import com.challenge.tipocambio.expose.model.SavedExchangeRateRequestDto;

public class ExchangeRateDtoBuilder {
    public ExchangeRateSearchFilterDto getBuildExchangeRateSearchFilterDto(ExchangeRateRequestDto request) {
        return ExchangeRateSearchFilterDto
                .builder()
                .amount(request.getAmount())
                .currencyTypeOriginId(request.getCurrencyTypeOriginId())
                .currencyTypeTargetId(request.getCurrencyTypeTargetId())
                .build();
    }

    public SavedExchangeRateDto getBuildSavedExchangeRateDto(SavedExchangeRateRequestDto request) {
        return SavedExchangeRateDto
                .builder()
                .currencyTypeId(request.getCurrencyTypeId())
                .currencyTypeTargetId(request.getCurrencyTypeTargetId())
                .amount(request.getAmount())
                .build();
    }
}
