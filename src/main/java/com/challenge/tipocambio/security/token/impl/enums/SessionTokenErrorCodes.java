package com.challenge.tipocambio.security.token.impl.enums;

import com.challenge.tipocambio.commons.ErrorCodeBase;
import lombok.Getter;

/**
 * @author dpena
 */
@Getter
public enum SessionTokenErrorCodes implements ErrorCodeBase {

    SESSION_TOKEN_INVALID(
            "SESSION_TOKEN_INVALID",
            "Token invalido",
            "Mensaje");

    private String code;
    private String message;
    private String title;


    SessionTokenErrorCodes(String code, String message, String title) {
        this.message = message;
        this.code = code;
        this.title = title;
    }
}
