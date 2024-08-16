package com.order.api.app.contoller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.api.app.client.UserClient;
import com.order.api.app.model.FooditemDetails;
import com.order.api.app.model.OrderInfo;
import com.order.api.app.model.RestaurantInfo;
import com.order.api.app.model.SearchFoodItem;
import com.order.api.app.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@PostMapping(value = "/search-by-name")
	public ResponseEntity<List<RestaurantInfo>> searchByName(@RequestBody Map<String, String> entity) {

		return orderService.searchByName(entity);
	}

	@PostMapping(value = "/search-by-fooditem")
	public ResponseEntity<List<SearchFoodItem>> searchByFoodItem(@RequestBody Map<String, String> entity) {

		return orderService.searchByFoodItem(entity);
	}

	@PostMapping(value = "/place-order")
	public ResponseEntity<String> placeOrder(@RequestBody Map entity) {

		return orderService.placeOrder(entity);
	}

	@PostMapping(value = "/rate-order")
	public ResponseEntity<String> rateOrder(@RequestBody Map entity) {
		return orderService.rateOrder(entity);

	}

	@GetMapping(value = "/get-all-food-items")
	public ResponseEntity<List<FooditemDetails>> getFoodAllItems() {

		return orderService.getAllFoodItems();

	}

	@PostMapping(value = "/get-all-order-details")
	public ResponseEntity<List<OrderInfo>> getAllOrderDetails(@RequestBody Map entity) {

		return orderService.getAllOrderDetails(entity);
	}

}