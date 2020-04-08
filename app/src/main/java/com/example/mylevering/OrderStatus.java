package com.example.mylevering;

import java.util.concurrent.TimeUnit;

public class OrderStatus extends Thread {

    private MyOrderFrag frag;

    public OrderStatus(MyOrderFrag myOrder) {
        frag = myOrder;
    }

    @Override
    public void run() {



        frag.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (frag.isVisible()) { // set status in the foreground
                    frag.setStatus(MyOrderFrag.UNSENT);
                } else { // set status in the background
                    frag.setStatusBackground(MyOrderFrag.UNSENT);
                }
            }
        });
        try {
            TimeUnit.SECONDS.sleep(5);
            frag.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (frag.isVisible()) {
                        frag.setStatus(MyOrderFrag.SENT);
                    } else {
                        frag.setStatusBackground(MyOrderFrag.SENT);
                    }
                }
            });
            TimeUnit.SECONDS.sleep(5);
            frag.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (frag.isVisible()) {
                        frag.setStatus(MyOrderFrag.IN_QUEUE);
                    } else {
                        frag.setStatusBackground(MyOrderFrag.IN_QUEUE);
                    }
                }
            });
            TimeUnit.SECONDS.sleep(5);
            frag.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (frag.isVisible()) {
                        frag.setStatus(MyOrderFrag.IN_PROGRESS);
                    } else {
                        frag.setStatusBackground(MyOrderFrag.IN_PROGRESS);
                    }
                }
            });
            TimeUnit.SECONDS.sleep(5);
            frag.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (frag.isVisible()) {
                        frag.setStatus(MyOrderFrag.COMPLETED);
                    } else {
                        frag.setStatusBackground(MyOrderFrag.COMPLETED);
                    }
                }
            });
        } catch (InterruptedException e) {
            return;
        }
    }
}
