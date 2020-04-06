package com.example.mylevering;

import android.os.Parcel;
import android.os.Parcelable;

public class ButterflyMenuOption implements Parcelable /*extends MenuOption*/ {

    private String title;
    private String price;
    private String calories;

    ButterflyMenuOption(String t, String p, String c) {
        this.setAll(t, p, c);
    }

    protected ButterflyMenuOption(Parcel in) {
        title = in.readString();
        price = in.readString();
        calories = in.readString();
    }

    public static final Creator<ButterflyMenuOption> CREATOR = new Creator<ButterflyMenuOption>() {
        @Override
        public ButterflyMenuOption createFromParcel(Parcel in) {
            return new ButterflyMenuOption(in);
        }

        @Override
        public ButterflyMenuOption[] newArray(int size) {
            return new ButterflyMenuOption[size];
        }
    };

    private void setAll(String title, String price, String calories){
        this.title = title;
        this.price = price;
        this.calories = calories;
    }

    public String getTitle() { return title; }
    public String getPrice() { return price; }
    public String getCalories() { return calories; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(price);
        dest.writeString(calories);
    }
}
