package com.order.api.app.model;

public class SearchFoodItem {
    private Integer restaurantid;
    private String restaurantname;
    private String restaurantaddress;
    private Float restaurantrating;
    private FoodItem foodItem;

   

    public SearchFoodItem(Integer restaurantid, String restaurantname, String restaurantaddress,
            Float restaurantrating, FoodItem foodItem) {
        this.restaurantid = restaurantid;
        this.restaurantname = restaurantname;
        this.restaurantaddress = restaurantaddress;
        this.restaurantrating = restaurantrating;
        this.foodItem = foodItem;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
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

    public String getRestaurantaddress() {
        return restaurantaddress;
    }

    public void setRestaurantaddress(String restaurantaddress) {
        this.restaurantaddress = restaurantaddress;
    }

  

    public Float getRestaurantrating() {
        return restaurantrating;
    }

    public void setRestaurantrating(Float restaurantrating) {
        this.restaurantrating = restaurantrating;
    }

}
