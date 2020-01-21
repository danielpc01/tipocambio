package com.challenge.tipocambio.expose;

import com.challenge.tipocambio.business.exchange.rate.ExchangeRateService;
import com.challenge.tipocambio.commons.api.rest.ResponseEnvelop;
import com.challenge.tipocambio.expose.handler.ExchangeRateDtoBuilder;
import com.challenge.tipocambio.expose.model.ExchangeRateRequestDto;
import com.challenge.tipocambio.expose.model.SavedExchangeRateRequestDto;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.challenge.tipocambio.commons.constants.EndpointConstant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author epena
 */

@RestController
@RequestMapping(EndpointConstant.API_BASE)
public class ExchangeRateRestController {

    private ExchangeRateService exchangeRateService;

    public ExchangeRateRestController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @ApiOperation(
            value = "Get exchange rate",
            notes = "Get exchange rate",
            response = ResponseEnvelop.class,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "/exchange-rate")
    @ResponseBody
    public Single<ResponseEntity> getExchangeRates(
            @ModelAttribute ExchangeRateRequestDto request) {
        ExchangeRateDtoBuilder dtoBuilder = new ExchangeRateDtoBuilder();
        return exchangeRateService.getExchangeRate(dtoBuilder.getBuildExchangeRateSearchFilterDto(request))
                .subscribeOn(Schedulers.io())
                .map(s -> ResponseEntity.ok(s));
    }

    @ApiOperation(
            value = "Save exchange rate",
            notes = "Save exchange rate",
            response = ResponseEnvelop.class,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/exchange-rate")
    @ResponseBody
    public Single<ResponseEntity> saveExchangeRate(
            @RequestBody SavedExchangeRateRequestDto request) {
        ExchangeRateDtoBuilder dtoBuilder = new ExchangeRateDtoBuilder();
        return exchangeRateService.saveExchangeRate(dtoBuilder.getBuildSavedExchangeRateDto(request))
                .subscribeOn(Schedulers.io())
                .map(s -> ResponseEntity.ok(s));
    }


}
