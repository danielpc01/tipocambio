package com.challenge.tipocambio.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "User")
@Getter
@NoArgsConstructor
@Setter
public class User extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "userName")
    private String userName;

    @Column(name = "key")
    private String key;

}