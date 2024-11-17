package com.projects.cravecart.service;

import com.projects.cravecart.dto.RestaurantRequest;
import com.projects.cravecart.dto.RestaurantResponse;
import com.projects.cravecart.entity.Restaurant;
import com.projects.cravecart.repository.RestaurantRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest){
        return transform(this.restaurantRepository.save(transform(restaurantRequest)));
    }

    public List<RestaurantResponse> getAllRestaurants(){
        List<Restaurant> restaurants = this.restaurantRepository.findAll();
        List<RestaurantResponse> restaurantResponses = null;
        if(!restaurants.isEmpty())
            restaurantResponses = restaurants.stream().map(this::transform).collect(Collectors.toList());
        return restaurantResponses;
    }

    public RestaurantResponse updateRestaurant(ObjectId restaurantId,RestaurantRequest restaurantRequest){
        Restaurant persistedRestaurant = this.restaurantRepository.findById(restaurantId).orElse(null);
        if(persistedRestaurant == null)
            return null;
        validateAndTransform(persistedRestaurant,transform(restaurantRequest));
        persistedRestaurant = this.restaurantRepository.save(persistedRestaurant);
        return transform(persistedRestaurant);
    }

    public boolean deleteRestaurant(ObjectId restaurantId){
        Restaurant restaurant = this.restaurantRepository.findById(restaurantId).orElse(null);
        if(restaurant == null)
            return false;
        this.restaurantRepository.deleteById(restaurantId);
        return true;
    }

    private void validateAndTransform(Restaurant restaurantOld,Restaurant restaurantNew){

    }

    private Restaurant transform(RestaurantRequest restaurantRequest){
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(restaurantRequest.getRestaurantName());
        restaurant.setRating(restaurantRequest.getRating());
        restaurant.setAddress(restaurantRequest.getAddress());
        restaurant.setMenuItems(restaurantRequest.getMenuItems() == null ? new ArrayList<>():restaurantRequest.getMenuItems());
        return restaurant;
    }

    private RestaurantResponse transform(Restaurant restaurant){
        RestaurantResponse restaurantResponse = new RestaurantResponse();
        restaurantResponse.setId(restaurant.getId().toString());
        restaurantResponse.setRestaurantName(restaurant.getRestaurantName());
        restaurantResponse.setRating(restaurant.getRating());
        restaurantResponse.setDescription(restaurant.getDescription());
        restaurantResponse.setAddress(restaurant.getAddress());
        restaurantResponse.setMenuItems(restaurant.getMenuItems());
        return restaurantResponse;
    }
}
