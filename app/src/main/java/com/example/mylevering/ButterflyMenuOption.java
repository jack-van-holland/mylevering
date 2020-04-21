package com.example.mylevering;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Set;

public class ButterflyMenuOption extends MenuOption implements Serializable {

    private String calories;
    private boolean available;
    private Set<String> restrictions;
    public ButterflyMenuOption(){}

    public ButterflyMenuOption(String t, String p, String c, String d, Set<String> r, Boolean a) {
        this.setAll(t, p, c, d, r, a);
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

    private void setAll(String title, String price, String calories, String description, Set<String> restrictions, boolean available){
        this.title = title;
        this.price = price;
        this.calories = calories;
        this.description = description;
        this.restrictions = restrictions;
        this.available = available;
    }

    public String getTitle() { return title; }
    public String getPrice() { return price; }
    public String getCalories() { return calories; }
    public String getDescription() { return description + "Contains: " + restrictions.toString().substring(1, restrictions.toString().length() - 1); }
    public Set<String> getRestrictions() { return restrictions; }
    public boolean isAvailable() { return available; }
    public void setCalories(String c) {
        calories = c;
    }


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

    public boolean isKosher() {
        return !restrictions.contains("Pork") || !(restrictions.contains("Chicken") && restrictions.contains("Dairy"));
    }

    public boolean isDairyFree() {
        return !restrictions.contains("Dairy");
    }

    public boolean isVegetarian() {
        return !(restrictions.contains("Pork") || restrictions.contains("Chicken"));
    }

    public boolean isVegan() {
        return isDairyFree() && isVegetarian();
    }

    public boolean isHalal() {
        return !restrictions.contains("Pork");
    }

    public boolean isGlutenFree() {
        return !restrictions.contains("Gluten");
    }

    public boolean isNutAllergy() {
        return false;
    }

    public boolean isShellfishAllergy() {
        return false;
    }
}
