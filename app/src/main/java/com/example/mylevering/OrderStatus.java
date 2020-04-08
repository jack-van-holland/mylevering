package com.example.mylevering;

import android.app.Activity;

import java.util.concurrent.TimeUnit;

public class OrderStatus extends Thread {

    private MainActivity act;

    public OrderStatus(MainActivity mainAcitivity) {
        act = mainAcitivity;
    }

    @Override
    public void run() {
        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((MyOrderFrag) act.myOrderFrag).setStatus(MyOrderFrag.UNSENT);
            }
        });
        try {
            TimeUnit.SECONDS.sleep(3);
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((MyOrderFrag) act.myOrderFrag).setStatus(MyOrderFrag.SENT);
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
