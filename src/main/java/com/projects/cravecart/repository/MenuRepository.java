package com.projects.cravecart.repository;

import com.projects.cravecart.entity.MenuItem;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MenuRepository extends MongoRepository<MenuItem, ObjectId> {

    void deleteById(ObjectId objectId);

    Optional<MenuItem> findById(ObjectId objectId);
}
