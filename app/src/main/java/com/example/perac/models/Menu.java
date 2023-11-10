package com.example.perac.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Menu implements Parcelable {
    private int photo;
    private String calorie;
    private String title;
    private String price;
    private String detail;
    private String rating;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.photo);
        dest.writeString(this.calorie);
        dest.writeString(this.title);
        dest.writeString(this.price);
        dest.writeString(this.detail);
        dest.writeString(this.rating);
    }

    public void readFromParcel(Parcel source) {
        this.photo = source.readInt();
        this.calorie = source.readString();
        this.title = source.readString();
        this.price = source.readString();
        this.detail = source.readString();
        this.rating = source.readString();
    }

    public Menu() {
    }

    protected Menu(Parcel in) {
        this.photo = in.readInt();
        this.calorie = in.readString();
        this.title = in.readString();
        this.price = in.readString();
        this.detail = in.readString();
        this.rating = in.readString();
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel source) {
            return new Menu(source);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };
}
