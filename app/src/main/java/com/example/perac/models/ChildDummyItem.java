package com.example.perac.models;

public class ChildDummyItem {
     public String itemNameChild;
    public int itemQuantityChild;

    public String getItemName() {
        return itemNameChild;
    }

    public void setItemName(String itemName) {
        this.itemNameChild = itemName;
    }

    public int getQuantity() {
        return itemQuantityChild;
    }

    public void setQuantity(int quantity) {
        this.itemQuantityChild = quantity;
    }



    public ChildDummyItem(String itemName, int quantity){
        this.itemNameChild = itemName;
        this.itemQuantityChild = quantity;
    }



}
