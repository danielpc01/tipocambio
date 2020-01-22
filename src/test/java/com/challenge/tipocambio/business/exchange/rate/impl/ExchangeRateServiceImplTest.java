package com.challenge.tipocambio.business.exchange.rate.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import com.challenge.tipocambio.business.exchange.rate.impl.exception.ExchangeRateNotFoundException;
import com.challenge.tipocambio.business.exchange.rate.impl.model.ExchangeRateDto;
import com.challenge.tipocambio.business.exchange.rate.impl.model.ExchangeRateSearchFilterDto;
import com.challenge.tipocambio.commons.utils.NumberUtils;
import com.challenge.tipocambio.persistence.model.CurrencyType;
import com.challenge.tipocambio.persistence.model.ExchangeRate;
import com.challenge.tipocambio.persistence.repository.CurrencyTypeJpaRepository;
import com.challenge.tipocambio.persistence.repository.ExchangeRateJpaRepository;

import io.reactivex.Single;

import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeRateServiceImplTest {
    private static final int SCALE = 4;

    private static final String AMOUNT = "100";
    private static final Long CURRENCY_TYPE_ORIGIN_ID = 1000L;
    private static final Long CURRENCY_TYPE_TARGET_ID = 1001L;
    private static final String CURRENCY_TYPE_ORIGIN_NAME = "SOLES";
    private static final String CURRENCY_TYPE_ORIGIN_SYMBOL = "S/";
    private static final String CURRENCY_TYPE_TARGET_SYMBOL = "$";
    private static final String CURRENCY_TYPE_TARGET_NAME = "DOLARES";
    private static final String EXCHANGE_RATE = "3.45";
    private ExchangeRateServiceImpl exchangeRateServiceImpl;

    @Mock
    private CurrencyTypeJpaRepository currencyTypeJpaRepository;
    @Mock
    private ExchangeRateJpaRepository exchangeRateJpaRepository;

    private ExchangeRateSearchFilterDto searchFilterDto;

    @Before
    public void setUp() {
        exchangeRateServiceImpl = new ExchangeRateServiceImpl(currencyTypeJpaRepository,exchangeRateJpaRepository);
        buildExchangeRateSearchFilterDto();
    }

    private void buildExchangeRateSearchFilterDto() {
        searchFilterDto = ExchangeRateSearchFilterDto
                .builder()
                .amount(AMOUNT)
                .currencyTypeOriginId(CURRENCY_TYPE_ORIGIN_ID)
                .currencyTypeTargetId(CURRENCY_TYPE_TARGET_ID)
                .build();
    }

    @Test
    public void getExchangeRateTestSuccess() {
        givenCurrencyOrigin(Boolean.TRUE);
        givenCurrencyTarget(Boolean.TRUE);
        givenExchangeRate(Boolean.TRUE);
        Single<ExchangeRateDto> exchangeRateDtoSingle = exchangeRateServiceImpl.getExchangeRate(searchFilterDto);
        ExchangeRateDto exchangeRateDto = exchangeRateDtoSingle.blockingGet();

        assertThat("Origin Amount",
                exchangeRateDto.getAmount(),
                is(NumberUtils.getInstance().roundDown(AMOUNT)));

        BigDecimal divide = NumberUtils.getInstance().roundDown(AMOUNT).divide(NumberUtils.getInstance().roundDown(EXCHANGE_RATE), SCALE, BigDecimal.ROUND_DOWN);
        assertThat("Amount with Exchange Rate",
                exchangeRateDto.getAmountWithExchangeRate(),
                is(divide));

        assertThat("Amount with Exchange Rate",
                exchangeRateDto.getExchangeRate(),
                is(NumberUtils.getInstance().roundDown(EXCHANGE_RATE)));
        assertThat("Currency type Origin Id",
                exchangeRateDto.getCurrencyOrigin().getId(),
                is(CURRENCY_TYPE_ORIGIN_ID));
        assertThat("Currency type Origin Name",
                exchangeRateDto.getCurrencyOrigin().getName(),
                is(CURRENCY_TYPE_ORIGIN_NAME));
        assertThat("Currency type Origin Symbol",
                exchangeRateDto.getCurrencyOrigin().getSymbol(),
                is(CURRENCY_TYPE_ORIGIN_SYMBOL));
    }

    @Test
    public void getExchangeRateTestCurrencyOriginNotFound() {
        givenCurrencyOrigin(Boolean.FALSE);

        TestObserver<ExchangeRateDto> subscriber = new TestObserver<>();

        Single<ExchangeRateDto> exchangeRateDtoSingle = exchangeRateServiceImpl.getExchangeRate(searchFilterDto);
        exchangeRateDtoSingle.subscribe(subscriber);
        subscriber.assertError(ExchangeRateNotFoundException.class);
    }

    @Test
    public void getExchangeRateTestCurrencyTargetNotFound() {
        givenCurrencyOrigin(Boolean.FALSE);
        givenCurrencyTarget(Boolean.TRUE);

        TestObserver<ExchangeRateDto> subscriber = new TestObserver<>();

        Single<ExchangeRateDto> exchangeRateDtoSingle = exchangeRateServiceImpl.getExchangeRate(searchFilterDto);
        exchangeRateDtoSingle.subscribe(subscriber);
        subscriber.assertError(ExchangeRateNotFoundException.class);

    }

    @Test
    public void getExchangeRateTestNotFound() {
        givenCurrencyOrigin(Boolean.TRUE);
        givenCurrencyTarget(Boolean.TRUE);
        givenExchangeRate(Boolean.FALSE);

        TestObserver<ExchangeRateDto> subscriber = new TestObserver<>();

        Single<ExchangeRateDto> exchangeRateDtoSingle = exchangeRateServiceImpl.getExchangeRate(searchFilterDto);
        exchangeRateDtoSingle.subscribe(subscriber);
        subscriber.assertError(ExchangeRateNotFoundException.class);

    }

    private void givenExchangeRate(Boolean found) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setAmount(new BigDecimal(EXCHANGE_RATE));

        Optional<ExchangeRate> optionalCurrency = found ? Optional.of(exchangeRate) : Optional.empty();


        when(exchangeRateJpaRepository.findByCurrencyTypeIdAndCurrencyTypeTargetIdAndActiveAndEnabled(
                CURRENCY_TYPE_ORIGIN_ID,CURRENCY_TYPE_TARGET_ID,Boolean.TRUE,Boolean.TRUE))
                .thenReturn(optionalCurrency);
    }

    private void givenCurrencyOrigin(Boolean found) {
        Optional<CurrencyType> optionalCurrency = found ? Optional.of(getCurrencySoles()) : Optional.empty();

        when(currencyTypeJpaRepository.findByIdAndEnabled(CURRENCY_TYPE_ORIGIN_ID,Boolean.TRUE))
                .thenReturn(optionalCurrency);
    }

    private void givenCurrencyTarget(Boolean found) {
        Optional<CurrencyType> optionalCurrency = found ? Optional.of(getCurrencyDolares()) : Optional.empty();

        when(currencyTypeJpaRepository.findByIdAndEnabled(CURRENCY_TYPE_TARGET_ID,Boolean.TRUE))
                .thenReturn(optionalCurrency);
    }

    private CurrencyType getCurrencySoles() {
        CurrencyType currencyType = new CurrencyType();
        currencyType.setName(CURRENCY_TYPE_ORIGIN_NAME);
        currencyType.setSymbol(CURRENCY_TYPE_ORIGIN_SYMBOL);
        currencyType.setId(CURRENCY_TYPE_ORIGIN_ID);
        return currencyType;
    }

    private CurrencyType getCurrencyDolares() {
        CurrencyType currencyType = new CurrencyType();
        currencyType.setName(CURRENCY_TYPE_TARGET_NAME);
        currencyType.setSymbol(CURRENCY_TYPE_TARGET_SYMBOL);
        currencyType.setId(CURRENCY_TYPE_TARGET_ID);
        return currencyType;
    }
}