package com.order.api.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.order.api.app.model.FoodItemRating;

public interface FoodItemRatingRepo extends CrudRepository<FoodItemRating, Integer> {

}
