package com.example.bytecode.javassist;

import java.util.Random;

public class ApiTest {

    public String queryGirlfriendCount(String boyfriendName) {
        return boyfriendName + "的前女友数量：" + (new Random().nextInt(10) + 1) + " 个";
    }
}

