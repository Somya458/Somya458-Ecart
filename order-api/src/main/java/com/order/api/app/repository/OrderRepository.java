package com.order.api.app.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.order.api.app.model.RestaurantInfo;

public interface OrderRepository extends JpaRepository<RestaurantInfo, Integer> {
	public Optional<RestaurantInfo> findByRestaurantnameAndRestaurantaddress(String restaurantname, String address);

    public Optional<RestaurantInfo> findByRestaurantid(Integer id);

    public List<RestaurantInfo> findByRestaurantnameContaining(String name);
    public Collection<? extends RestaurantInfo> findByRestaurantnameContaining(String string, Sort by);

}

