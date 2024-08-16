package com.api.common.app.contoller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.common.app.model.FoodItem;
import com.api.common.app.model.RestaurantDetails;
import com.api.common.app.service.CommonService;

@RestController
@RequestMapping(value = "/ecart")
public class CommonController {
    @Autowired
    private CommonService commonService;

    @GetMapping(value = "/get-restaurants")
    public ResponseEntity<ArrayList<RestaurantDetails>> getRestaurants() {

        return commonService.getRestaurants();

    }

    @PostMapping(value = "/get-fooditems")
    public ResponseEntity<List<FoodItem>> getFoodItems(@RequestBody Map<String, Integer> entity) {

        return commonService.getFoodItems(entity);

    }

}
