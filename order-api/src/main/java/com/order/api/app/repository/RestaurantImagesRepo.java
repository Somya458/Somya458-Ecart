package com.order.api.app.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.order.api.app.model.RestaurantImages;

import jakarta.transaction.Transactional;

public interface RestaurantImagesRepo extends CrudRepository<RestaurantImages, Integer> {
    @Query(value = "DELETE FROM restaurant_images where restaurant_id = :rId", nativeQuery = true)
    @Modifying
    @Transactional
    public void deleteByRestaurantid(Integer rId);
}
