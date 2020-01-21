package com.challenge.tipocambio.business.exchange.rate.impl.enums;

import com.challenge.tipocambio.commons.ErrorCodeBase;

import lombok.Getter;

/**
 * @author dpena
 */
@Getter
public enum ExchangeRateErrorCodes implements ErrorCodeBase {

    CURRENCY_NOT_FOUND(
            "CURRENCY_NOT_FOUND",
            "Moneda no encontrado",
            "Mensaje"),
    CURRENCY_ORIGIN_NOT_FOUND(
            "CURRENCY_ORIGIN_NOT_FOUND",
            "Moneda origen no encontrado",
            "Mensaje"),
    CURRENCY_TARGET_NOT_FOUND(
            "CURRENCY_TARGET_NOT_FOUND",
            "Moneda destino no encontrado",
            "Mensaje"),
    EXCHANGE_RATE_NOT_FOUND(
            "EXCHANGE_RATE_NOT_FOUND",
            "Tipo de cambio no encontrado",
            "Mensaje");

    private String code;
    private String message;
    private String title;


    ExchangeRateErrorCodes(String code, String message, String title) {
        this.message = message;
        this.code = code;
        this.title = title;
    }
}
