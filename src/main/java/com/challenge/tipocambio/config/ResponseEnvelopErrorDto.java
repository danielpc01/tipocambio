package com.challenge.tipocambio.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dpena
 */
@Getter
@Setter
@Builder
public class ResponseEnvelopErrorDto {

    private String code;
    private String message;
    private String title;
}
