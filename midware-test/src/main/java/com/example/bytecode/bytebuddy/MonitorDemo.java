package com.example.bytecode.bytebuddy;

import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class MonitorDemo {
    @RuntimeType
    public static Object intercept(@Origin Method method, @AllArguments Object[] args, @SuperCall Callable<?> callable) throws Exception {
        long start = System.currentTimeMillis();
        Object resObj = null;
        try {
            resObj = callable.call();
            return resObj;
        } finally {
            System.out.println("方法名称：" + method.getName());
            for (int i = 0; i < method.getParameterCount(); i++) {
                System.out.print("入参类型：" + method.getParameterTypes()[i].getTypeName());
                System.out.println("入参：" + args[i]);
            }
            System.out.println("出参类型：" + method.getReturnType().getName() + " 出参结果：" + resObj);
            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
        }
    }
}
