package com.example.perac.models;

import com.example.perac.R;

import java.util.ArrayList;

public class MenuData {
    private static int[] menuPhoto = {
            R.drawable.chocolate_milk,
            R.drawable.coffee_machiato,
            R.drawable.iced_chocolate,
            R.drawable.matcha_tea,
            R.drawable.oreo_chocolate,
            R.drawable.palm_sugar_coffee
    };

    private static String[] menuTitle = {
            "Chocolate Milk",
            "Coffee Machiato",
            "Iced Chocolate",
            "Matcha Tea",
            "Oreo Chocolate",
            "Palm Sugar Coffee"
    };

    private static String[] menuCalorie = {
            "500 Cal",
            "300 Cal",
            "450 Cal",
            "200 Cal",
            "500 Cal",
            "250 Cal"
    };

    private static String[] menuPrice = {
            "Rp40.000,-",
            "Rp30.000,-",
            "Rp35.000,-",
            "Rp40.000,-",
            "Rp40.000,-",
            "Rp25.000,-"
    };

    public static ArrayList<Menu> getListData(){
        ArrayList<Menu> list = new ArrayList<>();
        for(int position = 0; position < menuTitle.length; position++){
            Menu menu = new Menu();
            menu.setPrice(menuPrice[position]);
            menu.setCalorie(menuCalorie[position]);
            menu.setTitle(menuTitle[position]);
            menu.setPhoto(menuPhoto[position]);
            list.add(menu);
        }
        return list;
    }
}
