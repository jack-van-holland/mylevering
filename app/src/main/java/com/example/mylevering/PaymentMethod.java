package com.example.mylevering;

import java.io.Serializable;

public class PaymentMethod implements Serializable {

    private String cardName;
    private String balance;
    private String mealPlan;

    public PaymentMethod(String c, String b, String mp) {
        this.cardName = c;
        this.balance = b;
        this.mealPlan = mp;
    }

    public String getCardName() { return this.cardName; }
    public String getBalance() { return this.balance; }
    public String getMealPlan() { return this.mealPlan; }

}
