package com.challenge.tipocambio.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CurrencyType")
@Getter
@NoArgsConstructor
@Setter
public class CurrencyType extends BaseEntity {


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "symbol")
    private String symbol;

}