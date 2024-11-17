package com.projects.cravecart.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class MenuItemResponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean vegetarian;
}
