package com.example.mylevering;

import java.io.Serializable;

public class OrderDate implements Serializable {
    private int year;
    private int month;
    private int day;

    public OrderDate(){}

    public int getYear() {
        return year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
