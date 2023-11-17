package com.example.perac.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static List<CartItem> cartItems = new ArrayList<>();

    public static List<CartItem> getCartItems() {
        return cartItems;
    }

    public static void addToCart(Menu menu, int quantity) {
        CartItem existingItem = null;

        for (CartItem item : cartItems) {
            if (item.getProductId().equals(menu.getId())) {
                existingItem = item;
                break;
            }
        }

        if (existingItem != null) {
            existingItem.setQuantity(quantity);
        } else {
            CartItem newItem = new CartItem(menu.getId(), quantity);
            cartItems.add(newItem);
        }
    }

    public static void clearCart() {
        cartItems.clear();
    }

    public static void removeItem(int position) {
        cartItems.remove(position);
    }
}
