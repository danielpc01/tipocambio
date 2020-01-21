package com.challenge.tipocambio.business.exchange.rate.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import com.challenge.tipocambio.business.exchange.rate.ExchangeRateService;
import com.challenge.tipocambio.business.exchange.rate.SavedExchangeRateDto;
import com.challenge.tipocambio.business.exchange.rate.impl.enums.ExchangeRateErrorCodes;
import com.challenge.tipocambio.business.exchange.rate.impl.exception.ExchangeRateNotFoundException;
import com.challenge.tipocambio.business.exchange.rate.impl.handler.ExchangeRateDtoBuilder;
import com.challenge.tipocambio.business.exchange.rate.impl.model.ExchangeRateDto;
import com.challenge.tipocambio.business.exchange.rate.impl.model.ExchangeRateSearchFilterDto;
import com.challenge.tipocambio.commons.utils.NumberUtils;
import com.challenge.tipocambio.persistence.model.CurrencyType;
import com.challenge.tipocambio.persistence.model.ExchangeRate;
import com.challenge.tipocambio.persistence.repository.CurrencyTypeJpaRepository;
import com.challenge.tipocambio.persistence.repository.ExchangeRateJpaRepository;

import io.reactivex.Single;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final CurrencyTypeJpaRepository currencyTypeJpaRepository;
    private final ExchangeRateJpaRepository exchangeRateJpaRepository;

    @Autowired
    public ExchangeRateServiceImpl(CurrencyTypeJpaRepository currencyTypeJpaRepository,
                                   ExchangeRateJpaRepository exchangeRateJpaRepository) {
        this.currencyTypeJpaRepository = currencyTypeJpaRepository;
        this.exchangeRateJpaRepository = exchangeRateJpaRepository;
    }

    @Override
    public Single<ExchangeRateDto> getExchangeRate(ExchangeRateSearchFilterDto searchFilterDto) {
        return Single.create(singleSubscriber -> {

            Optional<CurrencyType> currencyTypeOrigin = currencyTypeJpaRepository.findByIdAndEnabled(searchFilterDto.getCurrencyTypeOriginId(),Boolean.TRUE);
            if (!currencyTypeOrigin.isPresent()) {
                singleSubscriber.onError(new ExchangeRateNotFoundException(ExchangeRateErrorCodes.CURRENCY_ORIGIN_NOT_FOUND));
            }

            Optional<CurrencyType> currencyTypeTarget = currencyTypeJpaRepository.findByIdAndEnabled(searchFilterDto.getCurrencyTypeTargetId(),Boolean.TRUE);
            if (!currencyTypeTarget.isPresent()) {
                singleSubscriber.onError(new ExchangeRateNotFoundException(ExchangeRateErrorCodes.CURRENCY_TARGET_NOT_FOUND));
            }

            Optional<ExchangeRate> exchangeRate = exchangeRateJpaRepository
                    .findByCurrencyTypeIdAndCurrencyTypeTargetIdAndActiveAndEnabled(
                            searchFilterDto.getCurrencyTypeOriginId(),
                            searchFilterDto.getCurrencyTypeTargetId(),
                            Boolean.TRUE,
                            Boolean.TRUE);

            if (exchangeRate.isPresent()) {
                ExchangeRateDtoBuilder dtoBuilder = new ExchangeRateDtoBuilder();

                singleSubscriber.onSuccess(
                        dtoBuilder.getBuildExchangeRate(
                                NumberUtils.getInstance().roundDown(searchFilterDto.getAmount()),
                                exchangeRate.get(),
                                currencyTypeOrigin.get(),
                                currencyTypeTarget.get()));
            } else {
                singleSubscriber.onError(new ExchangeRateNotFoundException(ExchangeRateErrorCodes.EXCHANGE_RATE_NOT_FOUND));
            }


        });
    }

    @Override
    @Transactional
    public Single<Boolean> saveExchangeRate(SavedExchangeRateDto exchangeRateDto) {
        return Single.create(singleSubscriber -> {

            Optional<CurrencyType> currencyTypeOrigin = currencyTypeJpaRepository.findByIdAndEnabled(exchangeRateDto.getCurrencyTypeId(),Boolean.TRUE);
            if (!currencyTypeOrigin.isPresent()) {
                singleSubscriber.onError(new ExchangeRateNotFoundException(ExchangeRateErrorCodes.CURRENCY_ORIGIN_NOT_FOUND));
            }

            Optional<CurrencyType> currencyTypeTarget = currencyTypeJpaRepository.findByIdAndEnabled(exchangeRateDto.getCurrencyTypeTargetId(),Boolean.TRUE);
            if (!currencyTypeTarget.isPresent()) {
                singleSubscriber.onError(new ExchangeRateNotFoundException(ExchangeRateErrorCodes.CURRENCY_TARGET_NOT_FOUND));
            }

            Optional<ExchangeRate> optionalExchangeRate = exchangeRateJpaRepository
                    .findByCurrencyTypeIdAndCurrencyTypeTargetIdAndActiveAndEnabled(
                            exchangeRateDto.getCurrencyTypeId(),
                            exchangeRateDto.getCurrencyTypeTargetId(),
                            Boolean.TRUE,
                            Boolean.TRUE);

            ExchangeRate exchangeRate;
            if (optionalExchangeRate.isPresent()) {
                exchangeRate = optionalExchangeRate.get();
            } else {
                exchangeRate = new ExchangeRate();
                exchangeRate.setActive(Boolean.TRUE);
                exchangeRate.setCurrencyType(currencyTypeOrigin.get());
                exchangeRate.setCurrencyTypeTarget(currencyTypeTarget.get());

            }

            exchangeRate.setDate(LocalDateTime.now());
            exchangeRate.setAmount(NumberUtils.getInstance().roundDown(exchangeRateDto.getAmount()));
            exchangeRateJpaRepository.save(exchangeRate);

            singleSubscriber.onSuccess(Boolean.TRUE);

        });
    }
}
