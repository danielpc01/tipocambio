package com.challenge.tipocambio.security.token;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SessionTokenDto {
    private String key;
}
