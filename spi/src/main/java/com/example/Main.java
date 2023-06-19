package com.example;

public class Main {
    public static void main(String[] args) {
        LoggerService service = LoggerService.getService();
        service.debug("111");
        service.info("1111");
        System.out.println("Hello world!");
    }
}