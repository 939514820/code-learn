package com.example.bytecode.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MonitorTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException {
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(BizService.class)
                .name("com.example.bytecode.bytebuddy.BizServiceNew")
                .method(ElementMatchers.named("queryUserInfo"))
                .intercept(MethodDelegation.to(MonitorDemo.class))
                .make();
        dynamicType.saveIn(new File("com.example.bytecode.bytebuddy"));
        Class<?> clazz = dynamicType.load(MonitorTest.class.getClassLoader())
                .getLoaded();
        Object instance = clazz.newInstance();

        clazz.getMethod("queryUserInfo", String.class).invoke(instance, "zhangsan");

    }
}
