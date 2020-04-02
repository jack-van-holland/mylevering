package com.example.mylevering;

import java.io.Serializable;

public class MenuOption implements Serializable {
    protected String title;
    protected String description;
    //protected Price price;

    public MenuOption() {
        title = "";
        description = "";
      //  price = new Price();
    }

    public MenuOption(String t, String d, Price p) {
        title = t;
        description = d;
        //price = p;
    }

    public String getDescription(){
        return description;
    }

    public String getTitle() {
        return title;
    }

    //public Price getPrice() {
      //  return price;
    //}

    //public void setPrice(Price p) {
      //  price = p;
   // }

    public void setTitle(String t) {
        title = t;
    }

    public void setDescription(String d) {
        description = d;
    }

}
