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
            "Palm Coffee"
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

    private static String[] menuDetail = {
            "A delightful beverage made by combining milk with rich " +
                    "and creamy chocolate, creating a sweet and satisfying " +
                    "drink.",

            "An espresso-based coffee featuring a shot of espresso \"stained\" " +
                    "or \"marked\" with a small amount of frothy milk, " +
                    "offering a bold coffee flavor with a touch of creaminess.",

            "A refreshing and indulgent cold beverage made with chocolate " +
                    "syrup or melted chocolate, mixed with cold milk and ice, " +
                    "perfect for those who crave a cool chocolatey treat.",

            "A traditional Japanese green tea powder that is whisked into hot " +
                    "water, producing a vibrant green tea with a unique, " +
                    "earthy flavor and numerous health benefits.",

            "A delightful fusion of crushed Oreo cookies with chocolate, " +
                    "often blended into a milkshake or incorporated into other " +
                    "desserts, providing a delicious combination of chocolate " +
                    "and cookie crunch.",

            "A specialty coffee sweetened with palm sugar, offering a " +
                    "distinct and natural sweetness with a hint of caramel " +
                    "flavor, providing a unique twist to the classic cup " +
                    "of coffee."
    };
    private static String[] menuRating ={
            "4.9",
            "4.8",
            "4.9",
            "4.8",
            "4,8",
            "4,9"
    };

    public static ArrayList<Menu> getListData(){
        ArrayList<Menu> list = new ArrayList<>();
        for(int position = 0; position < menuTitle.length; position++){
            Menu menu = new Menu();
            menu.setId(String.valueOf(position));
            menu.setPrice(menuPrice[position]);
            menu.setCalorie(menuCalorie[position]);
            menu.setTitle(menuTitle[position]);
            menu.setPhoto(menuPhoto[position]);
            menu.setDetail(menuDetail[position]);
            menu.setRating(menuRating[position]);
            list.add(menu);
        }
        return list;
    }

    public static Menu getDataByIndex(int index) {
        Menu menu = new Menu();
        menu.setId(String.valueOf(index));
        menu.setPrice(menuPrice[index]);
        menu.setCalorie(menuCalorie[index]);
        menu.setTitle(menuTitle[index]);
        menu.setPhoto(menuPhoto[index]);
        menu.setDetail(menuDetail[index]);
        menu.setRating(menuRating[index]);
        menu.setDetail(menuDetail[index]);
        menu.setRating(menuRating[index]);

        return menu;
    }
}
