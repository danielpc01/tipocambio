package com.challenge.tipocambio.expose.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author dpena
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SavedExchangeRateRequestDto {

    @JsonProperty("currencyTypeId")
    private Long currencyTypeId;

    @JsonProperty("currencyTypeTargetId")
    private Long currencyTypeTargetId;

    @JsonProperty("amount")
    private String amount;
}
