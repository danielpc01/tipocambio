package com.challenge.tipocambio.security.token.impl.enums;

import com.challenge.tipocambio.commons.ErrorCodeBase;
import lombok.Getter;

/**
 * @author dpena
 */
@Getter
public enum UserErrorCodes implements ErrorCodeBase {

    USER_OR_KEY_INVALID(
            "USER_OR_KEY_INVALID",
            "Usuario o Password invalido",
            "Mensaje");

    private String code;
    private String message;
    private String title;


    UserErrorCodes(String code, String message, String title) {
        this.message = message;
        this.code = code;
        this.title = title;
    }
}
