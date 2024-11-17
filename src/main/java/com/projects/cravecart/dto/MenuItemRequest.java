package com.projects.cravecart.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class MenuItemRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean vegetarian;
}
