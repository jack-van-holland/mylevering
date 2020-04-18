package com.example.mylevering;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Order implements Serializable, Comparable {

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
        if (d.getHours() >= 12) {
            cal.setAM("AM");
        } else {
            cal.setAM("PM");
        }

        if (d.getHours() == 0) {
            cal.setHour(12);
        } else if (d.getTime() > 12) {
            cal.setHour(d.getHours() - 12);
        } else {
            cal.setHour(d.getHours());
        }

        cal.setMinute(d.getMinutes());
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

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Order)) {
            throw new RuntimeException();
        }
        else {
            Order other = (Order) o;
            if (getCal().getYear() != other.getCal().getYear()) {
                return getCal().getYear() - other.getCal().getYear();
            } else if (getCal().getMonth() != other.getCal().getMonth()) {
                return getCal().getMonth() - other.getCal().getMonth();
            } else if(getCal().getDay() != other.getCal().getDay()) {
                return getCal().getDay() - other.getCal().getDay();
            } else if (!getCal().getAM().equals(other.getCal().getAM())) {
                if (getCal().getAM().equals("AM")) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (getCal().getHour() != other.getCal().getHour()) {
                return getCal().getHour() - other.getCal().getHour();
            } else if (getCal().getMinute() != other.getCal().getMinute()) {
                return getCal().getMinute() - other.getCal().getMinute();
            } else {
                return 0;
            }
        }
    }
}
