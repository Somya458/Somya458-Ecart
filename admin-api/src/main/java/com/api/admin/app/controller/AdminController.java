package com.api.admin.app.controller;

import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.admin.app.exception.RestaurantDoNotExist;
import com.api.admin.app.service.AdminService;

@RestController
@RequestMapping(value = "/ecart/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/add-restaurant")
    public ResponseEntity<String> addProduct(@RequestBody Map entity) {

        return adminService.addRestaurant(entity);
    }

    @PutMapping(value = "/edit-restaurant")
    public ResponseEntity<String> editRestaurant(@RequestBody Map entity) {

        return adminService.editRestaurant(entity);

    }

    @DeleteMapping(value = "/delete-restaurant")
    public ResponseEntity<String> deleteRestaurant(@RequestBody Map entity) {
        return adminService.deleteRestaurant(entity);
    }

	@PostMapping(value = "/add-fooditems")
	public ResponseEntity<String> addFoodItems(@RequestBody Map entity) {
		try {
			ResponseEntity<String> foodItems = adminService.addFoodItems(entity);
			return ResponseEntity.status(HttpStatus.SC_CREATED).body(foodItems.getBody());
		} catch (RestaurantDoNotExist e) {
			return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(e.getMessage());
		}
	}

    @PostMapping(value = "/edit-fooditems")
    public ResponseEntity<String> editFoodItems(@RequestBody Map entity) {
        return adminService.editFoodItems(entity);
    }

    @PostMapping(value = "/delete-fooditem")
    public ResponseEntity<String> deleteFooditem(@RequestBody Map entity) {
        return adminService.deleteFooditem(entity);
    }
}
