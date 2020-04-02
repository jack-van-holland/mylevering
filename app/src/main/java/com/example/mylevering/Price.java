package com.example.mylevering;

public class Price {
    int dollars;
    int cents;

    public Price(){
        dollars = 0;
        cents = 0;
    }
    public Price(int d, int c) {
        if (c > 99) {
            d += (c / 100);
            c = (c % 100);
        }
        dollars = d;
        cents = c;
    }

    public String toString() {
        return dollars + "." + cents;
    }

    public void add(int d, int c){
        if (cents + c > 99) {
            d += ((cents + c) / 100);
            c = ((cents + c) % 100);
        }
        dollars = d;
        cents = c;
    }
}
