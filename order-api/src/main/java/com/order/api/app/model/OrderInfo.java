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
@Table(name = "order_info")
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", unique = true, updatable = false, nullable = false)
    private Integer orderid;

    @Column(name = "user_id", unique = false, updatable = false, nullable = false)
    private Integer userid;

    @Column(name = "restaurant_id", unique = false, updatable = true, nullable = false)
    private Integer restaurantid;

    @Column(name = "restaurant_name", unique = false, updatable = true, nullable = false)
    private String restaurantname;

    @Column(name = "total_amount", unique = false, updatable = true, nullable = false)
    private Integer totalamount = 0;

    @Column(name = "order_flag", unique = false, updatable = true, nullable = false)
    private Integer orderflag = 0;

    @Column(name = "delivery_address", unique = false, updatable = true, nullable = false)
    private String deliveryaddress;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderinfo")
    private List<OrderFoodItems> orderFoodItems = new ArrayList<OrderFoodItems>();

    
}
