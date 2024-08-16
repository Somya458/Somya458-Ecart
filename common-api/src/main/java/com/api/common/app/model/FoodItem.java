package com.api.common.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "food_items")
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_item_id", unique = true, updatable = false, nullable = false)
    private Integer fooditemid;

    @Column(name = "food_name", unique = false, updatable = true, nullable = false)
    private String foodname;

    @Column(name = "description", unique = false, updatable = true, nullable = false)
    private String description;

    @Column(name = "price", unique = false, updatable = true, nullable = false)
    private Integer price;

    @Column(name = "image", unique = false, updatable = true, nullable = false)
    private String image;

    @Column(name = "food_item_rating", unique = false, updatable = true, nullable = true)
    private Double fooditemrating = 0.0;

    @Column(name = "numofrating", unique = false, updatable = true, nullable = true)
    private Integer numofrating = 0;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private RestaurantInfo restaurantInfo;

    public Integer getNumofrating() {
        return numofrating;
    }

    public void setNumofrating(Integer numofrating) {
        this.numofrating = numofrating;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public RestaurantInfo getRestaurantInfo() {
        return restaurantInfo;
    }

    public void setRestaurantInfo(RestaurantInfo restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }

    public Double getFooditemrating() {
        return fooditemrating;
    }

    public void setFooditemrating(Double fooditemrating) {
        this.fooditemrating = fooditemrating;
    }

}
