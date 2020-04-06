package com.example.mylevering;

import java.io.Serializable;

public class Order implements Serializable {

    private String title;
    private String description;
    private String price;
    private String scheduledTime;
    private String paymentMethod;
    private int status;

    public Order(MenuOption menuOption, String schedTime, String method, int stat) {
        title = menuOption.getTitle();
        description = menuOption.getDescription();
        price = menuOption.getPrice();
        scheduledTime = schedTime;
        paymentMethod = method;
        status = stat;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public int getStatus() {
        return status;
    }

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

    public void setStatus(int status) {
        this.status = status;
    }
}
