package com.example.agent;

import java.lang.instrument.Instrumentation;
//-javaagent:D:\IdeaProjects\code-learn\javaagent\target\javaagent-0.0.1-SNAPSHOT.jar=testargs
//
public class MyAgent {

    //JVM 首先尝试在代理类上调用以下方法
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("嗨！JavaAgent " + agentArgs);
    }

    //如果代理类没有实现上面的方法，那么 JVM 将尝试调用该方法
    public static void premain(String agentArgs) {
    }

}
