package com.example.mylevering;

import java.io.Serializable;

public class MenuOption implements Serializable {
    protected String title;
    protected String description;
    protected String price;
    protected String instructions;

    public MenuOption() {
        title = "";
        description = "";
        price = "";
    }

    public MenuOption(String t, String d, String p) {
        title = t;
        description = d;
        price = p;
    }

    public String getDescription(){
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String p) {
        price = p;
    }

    public void setTitle(String t) {
        title = t;
    }

    public void setDescription(String d) {
        description = d;
    }
}
