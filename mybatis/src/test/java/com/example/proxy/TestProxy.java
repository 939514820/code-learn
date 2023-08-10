package com.example.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

@Slf4j
public class TestProxy {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        log.info(userService.getName());
        log.info("start");
        PerfLog log1 = new PerfLog(userService);
        UserService proxyInstance = (UserService) Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),userService.getClass().getInterfaces(), log1);
        proxyInstance.getName();
        log.info("end");
    }
}
