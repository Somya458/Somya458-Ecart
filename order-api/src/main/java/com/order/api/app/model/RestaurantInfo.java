package com.order.api.app.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "restaurant_info")
public class RestaurantInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id", unique = true, updatable = false, nullable = false)
    private Integer restaurantid;

    @Column(name = "restaurant_name", unique = false, updatable = true, nullable = false)
    private String restaurantname;

    @Column(name = "address", unique = false, updatable = true, nullable = false)
    private String restaurantaddress;

    @Column(name = "restaurant_rating", unique = false, updatable = true, nullable = true)
    private Float restaurantrating = 0f;

    @Column(name = "numofrating", unique = false, updatable = true, nullable = true)
    private Integer numofrating = 0;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantInfo")
    private List<RestaurantImages> restaurantimages = new ArrayList<RestaurantImages>();

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantInfo")
    private List<FoodItem> foodItem = new ArrayList<FoodItem>();

}
