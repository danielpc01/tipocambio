package com.challenge.tipocambio.business.exchange.rate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SavedExchangeRateDto {
    private Long currencyTypeId;
    private Long currencyTypeTargetId;
    private String amount;
}
