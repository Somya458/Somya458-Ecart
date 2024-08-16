package com.order.api.app.model;

public class FooditemDetails {
   
    private int restaurantid;
    private FoodItem foodItem;
    private String restaurantname;
    public int getRestaurantid() {
        return restaurantid;
    }
    public void setRestaurantid(int restaurantid) {
        this.restaurantid = restaurantid;
    }
    public FoodItem getFoodItem() {
        return foodItem;
    }
    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }
    public String getRestaurantname() {
        return restaurantname;
    }
    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
    }
    public FooditemDetails(int restaurantid, FoodItem foodItem, String restaurantname) {
        this.restaurantid = restaurantid;
        this.foodItem = foodItem;
        this.restaurantname = restaurantname;
    }
}
