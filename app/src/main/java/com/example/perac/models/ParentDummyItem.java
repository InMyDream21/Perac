package com.example.perac.models;

public class ParentDummyItem {
    public String orderIDParent;
    public  String orderDateParent;
    public int orderTotalPriceParent;

    public ParentDummyItem(String orderID, String orderDate, int orderTotalPrice) {
        this.orderIDParent = orderID;
        this.orderDateParent = orderDate;
        this.orderTotalPriceParent = orderTotalPrice;
    }
}
