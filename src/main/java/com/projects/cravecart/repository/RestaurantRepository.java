package com.projects.cravecart.repository;

import com.projects.cravecart.entity.Restaurant;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, ObjectId> {
}
