package com.challenge.tipocambio.business.exchange.rate.impl.exception;

import com.challenge.tipocambio.commons.ErrorCodeBase;
import com.challenge.tipocambio.commons.exception.AbstractRestException;

import org.springframework.http.HttpStatus;

/**
 * @author dpena
 */
public class ExchangeRateNotFoundException extends AbstractRestException {

    private final transient ErrorCodeBase errorCode;

    public ExchangeRateNotFoundException(ErrorCodeBase errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    @Override
    public ErrorCodeBase getErrorCode() {
        return errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }


}
