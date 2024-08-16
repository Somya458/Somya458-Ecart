package com.order.api.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.order.api.app.model.OrderFoodItems;

public interface OrderFoodItemsRepo extends CrudRepository<OrderFoodItems, Integer> {

}
