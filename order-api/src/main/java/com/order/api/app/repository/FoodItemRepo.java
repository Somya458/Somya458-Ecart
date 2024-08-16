package com.order.api.app.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.order.api.app.model.FoodItem;

public interface FoodItemRepo extends CrudRepository<FoodItem, Integer> {
    @Query(value = "SELECT* FROM food_items s WHERE s.restaurant_id = :rId AND s.food_name =:name", nativeQuery = true)
    public Optional<FoodItem> findByRestaurantidAndFoodname(Integer rId, String name);

    public List<FoodItem> findByFoodnameContaining(String name);

    public Collection<? extends FoodItem> findByFoodnameContaining(String string, Sort by);

    public List<FoodItem> findAll();

}
