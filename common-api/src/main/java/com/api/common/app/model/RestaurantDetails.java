package com.api.common.app.model;

import java.util.List;

public class RestaurantDetails {

    private String restaurantname;
    private int restaurantid;
    private String restaurantaddress;
    private List<RestaurantImages> restaurantimages;
    private float  restaurantrating;

    public float getRestaurantrating() {
        return restaurantrating;
    }

    public void setRestaurantrating(float restaurantrating) {
        this.restaurantrating = restaurantrating;
    }

   

    public RestaurantDetails(String restaurantname, int restaurantid, String restaurantaddress,
            List<RestaurantImages> restaurantimages, float restaurantrating) {
        this.restaurantname = restaurantname;
        this.restaurantid = restaurantid;
        this.restaurantaddress = restaurantaddress;
        this.restaurantimages = restaurantimages;
        this.restaurantrating = restaurantrating;
    }

    public String getRestaurantname() {
        return restaurantname;
    }

    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
    }

    public int getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(int restaurantid) {
        this.restaurantid = restaurantid;
    }

    public List<RestaurantImages> getRestaurantimages() {
        return restaurantimages;
    }

    public void setRestaurantimages(List<RestaurantImages> restaurantimages) {
        this.restaurantimages = restaurantimages;
    }

    public String getRestaurantaddress() {
        return restaurantaddress;
    }

    public void setRestaurantaddress(String restaurantaddress) {
        this.restaurantaddress = restaurantaddress;
    }

}
