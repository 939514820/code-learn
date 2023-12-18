package com.example.agent;

public class ApiTest {
    //    https://bugstack.cn/md/other/road-map.html
    public static void main(String[] args) throws InterruptedException {
        ApiTest apiTest = new ApiTest();
        apiTest.echoHi();
    }

    private void echoHi() throws InterruptedException {
        System.out.println("hi agent");
        Thread.sleep((long) (Math.random() * 500));
    }

}
