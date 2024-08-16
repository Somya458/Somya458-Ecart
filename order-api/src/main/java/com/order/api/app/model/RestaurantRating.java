package com.order.api.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "restaurant_rating")
public class RestaurantRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_rating_id", unique = true, updatable = false, nullable = false)
    private Integer restaurantratingid;

    @Column(name = "name", unique = false, updatable = true, nullable = false)
    private String name;

    @Column(name = "restaurant_id", unique = false, updatable = true, nullable = false)
    private Integer restaurantid;

    @Column(name = "restaurant_name", unique = false, updatable = true, nullable = false)
    private String restaurantname;

    @Column(name = "restaurant_rating", unique = false, updatable = true, nullable = false)
    private Float restaurantrating;

    @Column(name = "restaurant_review", unique = false, updatable = true, nullable = false)
    private String restaurantreview;

}
