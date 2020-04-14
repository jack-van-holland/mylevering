package com.example.mylevering;

import java.io.Serializable;

public class Order implements Serializable {

    private static long nextId = 0;

    private String title;
    private String description;
    private String price;
    private String scheduledTime;
    private String paymentMethod;
    private int status;
    private boolean favorite;
    private String type;
    private String id;

    public Order(MenuOption menuOption, String from, String schedTime, String method, int stat, boolean fav) {
        this.title = menuOption.getTitle();
        this.description = menuOption.getDescription();
        this.price = menuOption.getPrice();
        this.scheduledTime = schedTime;
        this.paymentMethod = method;
        this.status = stat;
        this.favorite = fav;
        this.type = from;
        this.id = String.format("o-%04d", ++nextId);
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPrice() { return this.price; }

    public String getScheduledTime() {
        return this.scheduledTime;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public int getStatus() {
        return this.status;
    }

    public boolean isFavorite() {
        return this.favorite;
    }

    public String getType() {
        return this.type;
    }

    public String getId() { return this.id; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setType(String type) {
        this.type = type;
    }


}
