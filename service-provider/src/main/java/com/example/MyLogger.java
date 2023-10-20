package com.example;


public class MyLogger implements Logger {
    @Override
    public void info(String msg) {
        System.out.println("info:" + msg);
    }

    @Override
    public void debug(String msg) {
        System.out.println("debug:" + msg);
    }
}
