package com.example.perac.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public String date, userId, orderId, total;
    public List<CartItem> cartItems;

    public Order() {
    }

    public Order(String date, String userId, List<CartItem> cartItems, String total) {
        this.date = date;
        this.userId = userId;
        this.cartItems = cartItems;
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}

