package com.challenge.tipocambio.commons.exception;

import com.challenge.tipocambio.commons.ErrorCodeBase;

import org.springframework.http.HttpStatus;

/**
 * @author dpena
 */
public abstract class AbstractRestException extends RuntimeException {

    public AbstractRestException(String message) {
        super(message);
    }
    public abstract ErrorCodeBase getErrorCode();
    public abstract HttpStatus getHttpStatus();
}
