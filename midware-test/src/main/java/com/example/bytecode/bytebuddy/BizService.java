package com.example.bytecode.bytebuddy;

public class BizService {
    public String queryUserInfo(String userid) {
        System.out.println("biz queryUserInfo");
        return userid;
    }
}
