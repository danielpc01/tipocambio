package com.challenge.tipocambio.expose;

import com.challenge.tipocambio.business.exchange.rate.ExchangeRateService;
import com.challenge.tipocambio.commons.api.rest.ResponseEnvelop;
import com.challenge.tipocambio.commons.constants.EndpointConstant;
import com.challenge.tipocambio.expose.handler.ExchangeRateDtoBuilder;
import com.challenge.tipocambio.expose.handler.LoginDtoBuilder;
import com.challenge.tipocambio.expose.model.ExchangeRateRequestDto;
import com.challenge.tipocambio.expose.model.SavedExchangeRateRequestDto;
import com.challenge.tipocambio.expose.model.UserRequestDto;
import com.challenge.tipocambio.security.token.SessionTokenService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author epena
 */

@RestController
@RequestMapping(EndpointConstant.API_BASE)
public class LoginRestController {

    private SessionTokenService sessionTokenService;

    public LoginRestController(SessionTokenService sessionTokenService) {
        this.sessionTokenService = sessionTokenService;
    }

    @ApiOperation(
            value = "Login",
            notes = "Login",
            response = ResponseEnvelop.class,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/login")
    @ResponseBody
    public Single<ResponseEntity> login(
            @RequestBody UserRequestDto request) {
        return sessionTokenService.createSessionToken(request.getUserName(),request.getUserKey())
                .subscribeOn(Schedulers.io())
                .map(s -> ResponseEntity.ok(s));
    }


}
