package com.example.proxy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class PerfLog implements InvocationHandler {
    private Object object;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long l = System.currentTimeMillis();
        Object result = method.invoke(object, args);
        log.info("end:{}", System.currentTimeMillis() - l);
        return result;
    }
}
