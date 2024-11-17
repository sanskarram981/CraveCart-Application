package com.projects.cravecart.controller;

import com.projects.cravecart.dto.RestaurantRequest;
import com.projects.cravecart.dto.RestaurantResponse;
import com.projects.cravecart.service.RestaurantService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantResponse> addRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        return new ResponseEntity<>(this.restaurantService.addRestaurant(restaurantRequest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurants(){
        List<RestaurantResponse> restaurantResponses = this.restaurantService.getAllRestaurants();
        if(restaurantResponses != null)
            return new ResponseEntity<>(restaurantResponses,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponse> updateRestaurant(@PathVariable ObjectId restaurantId,@RequestBody RestaurantRequest restaurantRequest){
        RestaurantResponse restaurantResponse = this.restaurantService.updateRestaurant(restaurantId,restaurantRequest);
        if(restaurantResponse == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(restaurantResponse,HttpStatus.OK);
    }

    @DeleteMapping(("/{restaurantId}"))
    public ResponseEntity<Void> deleteRestaurant(@PathVariable ObjectId restaurantId){
        boolean isDeleted = this.restaurantService.deleteRestaurant(restaurantId);
        if(isDeleted)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
