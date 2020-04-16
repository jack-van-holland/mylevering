package com.example.mylevering;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ButterflyMenuOption extends MenuOption implements Serializable {

    private String calories;


    ButterflyMenuOption(String t, String p, String c, String d) {
        this.setAll(t, p, c, d);
    }

/*    protected ButterflyMenuOption(Parcel in) {
        title = in.readString();
        price = in.readString();
        calories = in.readString();
        description = in.readString();
    }*/
/*
    public static final Creator<ButterflyMenuOption> CREATOR = new Creator<ButterflyMenuOption>() {
        @Override
        public ButterflyMenuOption createFromParcel(Parcel in) {
            return new ButterflyMenuOption(in);
        }

        @Override
        public ButterflyMenuOption[] newArray(int size) {
            return new ButterflyMenuOption[size];
        }
    };*/

    private void setAll(String title, String price, String calories, String description){
        this.title = title;
        this.price = price;
        this.calories = calories;
        this.description = description;
    }

    public String getTitle() { return title; }
    public String getPrice() { return price; }
    public String getCalories() { return calories; }
    public String getDescription() { return description; }



    /*
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(price);
        dest.writeString(calories);
        dest.writeString(description);
        dest.writeString(instructions);
    }*/
}
