package com.challenge.tipocambio.business.exchange.rate;

import com.challenge.tipocambio.business.exchange.rate.impl.model.ExchangeRateDto;
import com.challenge.tipocambio.business.exchange.rate.impl.model.ExchangeRateSearchFilterDto;
import io.reactivex.Single;


public interface ExchangeRateService {

    Single<ExchangeRateDto> getExchangeRate(ExchangeRateSearchFilterDto searchFilterDto);

    Single<Boolean> saveExchangeRate(SavedExchangeRateDto exchangeRateDto);
}
