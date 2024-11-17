package com.projects.cravecart.dto;

import com.projects.cravecart.entity.Address;
import com.projects.cravecart.entity.MenuItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantResponse {
    private String id;
    private String restaurantName;
    private String description;
    private double rating;
    private Address address;
    private List<MenuItem> menuItems;
}
