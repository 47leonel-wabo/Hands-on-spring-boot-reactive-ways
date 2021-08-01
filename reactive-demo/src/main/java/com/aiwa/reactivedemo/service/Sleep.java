package com.aiwa.reactivedemo.service;

public class Sleep {
    public static void sleepSecond(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
