package com.challenge.tipocambio.security.token.impl.exception;

import com.challenge.tipocambio.commons.ErrorCodeBase;
import com.challenge.tipocambio.commons.exception.AbstractRestException;
import org.springframework.http.HttpStatus;

/**
 * @author dpena
 */
public class SessionNotFoundException extends AbstractRestException {

    private final transient ErrorCodeBase errorCode;

    public SessionNotFoundException(ErrorCodeBase errorCode) {
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
