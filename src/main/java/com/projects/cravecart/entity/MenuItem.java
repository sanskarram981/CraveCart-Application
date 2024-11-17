package com.projects.cravecart.entity;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document(collection = "menu_items")
@Data
public class MenuItem {
    @Id
    @Field("_id")
    private ObjectId id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean vegetarian;
}
