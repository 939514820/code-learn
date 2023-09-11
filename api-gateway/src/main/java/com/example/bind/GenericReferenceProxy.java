package com.example.bind;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.dubbo.rpc.service.GenericService;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class GenericReferenceProxy implements MethodInterceptor {
    private GenericService genericService;
    private String methodName;

    public GenericReferenceProxy(GenericService genericService, String methodName) {
        this.genericService = genericService;
        this.methodName = methodName;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String[] types = new String[method.getParameters().length];
        int i = 0;
        for (Parameter parameter : method.getParameters()) {
            types[i++] = parameter.getName();
        }
        Object result = genericService.$invoke(methodName, types, objects);

        return result;
    }
}
