package com.order.api.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.order.api.app.model.RestaurantRating;

public interface RestaurantRatingRepo extends CrudRepository<RestaurantRating, Integer> {

}
