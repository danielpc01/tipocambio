package com.challenge.tipocambio.persistence.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ExchangeRate")
@Getter
@NoArgsConstructor
@Setter
public class ExchangeRate extends BaseEntity {

    @Column(name = "active")
    private Boolean active;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currencyTypeId", nullable = false)
    private CurrencyType currencyType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currencyTypeTargetId", nullable = false)
    private CurrencyType currencyTypeTarget;
}
