package com.order.api.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "food_item_rating")
public class FoodItemRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_item_rating_id", unique = true, updatable = false, nullable = false)
    private Integer fooditemratingid;

    @Column(name = "name", unique = false, updatable = true, nullable = false)
    private String name;

    @Column(name = "restaurant_id", unique = false, updatable = true, nullable = false)
    private Integer restaurantid;

    @Column(name = "restaurant_name", unique = false, updatable = true, nullable = false)
    private String restaurantname;

    @Column(name = "fooditem_id", unique = false, updatable = true, nullable = false)
    private Integer fooditemid;

    @Column(name = "food_name", unique = false, updatable = true, nullable = false)
    private String foodname;

    @Column(name = "food_item_rating", unique = false, updatable = true, nullable = false)
    private Double fooditemrating;

    @Column(name = "food_item_review", unique = false, updatable = true, nullable = false)
    private String fooditemreview;

    public Integer getFooditemratingid() {
        return fooditemratingid;
    }

    public void setFooditemratingid(Integer fooditemratingid) {
        this.fooditemratingid = fooditemratingid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(Integer restaurantid) {
        this.restaurantid = restaurantid;
    }

    public String getRestaurantname() {
        return restaurantname;
    }

    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
    }

    public Integer getFooditemid() {
        return fooditemid;
    }

    public void setFooditemid(Integer fooditemid) {
        this.fooditemid = fooditemid;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFooditemreview() {
        return fooditemreview;
    }

    public void setFooditemreview(String fooditemreview) {
        this.fooditemreview = fooditemreview;
    }

    public Double getFooditemrating() {
        return fooditemrating;
    }

    public void setFooditemrating(Double fooditemrating) {
        this.fooditemrating = fooditemrating;
    }
}
