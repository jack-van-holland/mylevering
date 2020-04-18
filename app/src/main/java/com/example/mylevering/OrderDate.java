package com.example.mylevering;

import java.io.Serializable;

public class OrderDate implements Serializable {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String AM;

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

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getAM() {
        return AM;
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

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setAM(String AM) {
        this.AM = AM;
    }
}
