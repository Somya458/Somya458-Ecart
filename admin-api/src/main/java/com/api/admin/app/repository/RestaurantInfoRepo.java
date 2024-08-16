package com.api.admin.app.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.admin.app.model.RestaurantInfo;

public interface RestaurantInfoRepo extends JpaRepository<RestaurantInfo, Integer> {
    public Optional<RestaurantInfo> findByRestaurantnameAndRestaurantaddress(String restaurantname, String address);

    public Optional<RestaurantInfo> findByRestaurantid(Integer id);

    public List<RestaurantInfo> findByRestaurantnameContaining(String name);

    // @Query("SELECT p FROM RestaurantInfo p WHERE " +
    // "p.restaurantname LIKE CONCAT('%',:query, '%')")
    // List<RestaurantInfo> searchRestaurantName(String query);

    public Collection<? extends RestaurantInfo> findByRestaurantnameContaining(String string, Sort by);

}
