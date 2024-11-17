package com.projects.cravecart.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "restaurants")
@Data
public class Restaurant {
    @Id
    @Field("_id")
    private ObjectId id;
    @Indexed(unique = true)
    private String restaurantName;
    private String description;
    private double rating;
    private Address address ;
    @DBRef
    List<MenuItem> menuItems = new ArrayList<>();
}
