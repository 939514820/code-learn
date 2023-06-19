package com.example;

import java.util.ServiceLoader;

public class SpiTest {
    public static void main(String[] args) {
        ServiceLoader<Logger> loader = ServiceLoader.load(Logger.class);
        LoggerService service = LoggerService.getService();
        service.debug("111");
        service.info("1111");
        System.out.println("Hello world!");
    }
}
