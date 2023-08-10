package com.example.proxy;

public class UserServiceImpl implements UserService{
    @Override
    public String getName() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "name";
    }
}
