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

    private static int[] menuPrice = {
            40000,
            30000,
            35000,
            40000,
            40000,
            25000
    };

    private static String detail = "lorem ipsum dolor gw pusing anjir";
    private static String rating = "4.8";

    public static ArrayList<Menu> getListData(){
        ArrayList<Menu> list = new ArrayList<>();
        for(int position = 0; position < menuTitle.length; position++){
            Menu menu = new Menu();
            menu.setId(String.valueOf(position));
            menu.setPrice(menuPrice[position]);
            menu.setCalorie(menuCalorie[position]);
            menu.setTitle(menuTitle[position]);
            menu.setPhoto(menuPhoto[position]);
            menu.setDetail(detail);
            menu.setRating(rating);
            list.add(menu);
        }
        return list;
    }
}
