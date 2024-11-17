package com.projects.cravecart.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Address {

    private String streetAddress;
    private String city;
    private String zipCode;
    private String state;
    private String country;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
