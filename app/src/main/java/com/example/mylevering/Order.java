package com.example.mylevering;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Order implements Serializable {

    private MenuOption menu;
    private String scheduledTime;
    private String paymentMethod;
    private int status;
    private boolean favorite;
    private String type;
    private String id;
    private OrderDate cal;


    public Order() {}

    public Order(MenuOption menuOption, String from, String schedTime, String method, int stat, boolean fav) {
        menu = menuOption;
        this.scheduledTime = schedTime;
        this.paymentMethod = method;
        this.status = stat;
        this.favorite = fav;
        this.type = from;
        cal = new OrderDate();
        Date d = Calendar.getInstance().getTime();
        cal.setDay(d.getDate());
        cal.setMonth(d.getMonth() + 1);
        cal.setYear(d.getYear() + 1900);
    }

    public FreshMenuOption getFreshMenu() {
        if (menu instanceof FreshMenuOption) {
            return (FreshMenuOption) menu;
        }
        else {
            return null;
        }
    }

    public ButterflyMenuOption getButterflyMenu() {
        if (menu instanceof ButterflyMenuOption) {
            return (ButterflyMenuOption) menu;
        }
        else {
            return null;
        }
    }

    public OrderDate getCal() {
        return cal;
    }


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

    public void setButterflyMenu(ButterflyMenuOption m) {
        this.menu = m;
    }

    public void setFreshMenu(FreshMenuOption m) {
        this.menu = m;
    }

    public void setCal(OrderDate c){
        cal = c;
    }

    public void setID(String i) {
        this.id = i;
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
