package com.order.api.app.model;

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
@Table(name = "order_food_items")
public class OrderFoodItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_food_items_id", unique = true, updatable = false, nullable = false)
    private Integer orderfooditemsid;

    @Column(name = "food_item_id", unique = false, updatable = true, nullable = false)
    private Integer fooditemid;

    @Column(name = "food_name", unique = false, updatable = true, nullable = false)
    private String foodname;

    @Column(name = "quantity", unique = false, updatable = true, nullable = false)
    private Integer quantity = 0;

    @Column(name = "amount", unique = false, updatable = true, nullable = false)
    private Integer amount = 0;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderInfo orderinfo;

    public Integer getOrderfooditemsid() {
        return orderfooditemsid;
    }

    public void setOrderfooditemsid(Integer orderfooditemsid) {
        this.orderfooditemsid = orderfooditemsid;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public OrderInfo getOrderinfo() {
        return orderinfo;
    }

    public void setOrderinfo(OrderInfo orderinfo) {
        this.orderinfo = orderinfo;
    }

}
