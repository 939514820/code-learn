package com.example.bytecode;


import lombok.Data;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class MainTest {
    public static void main(String[] args) throws Exception {
        test("newField", "newField1");
        test1();

    }

    public static void test1() {

        Map<String, Class<?>> fields1 = new HashMap<>();
        fields1.put("newField", String.class);
        fields1.put("newField1", String.class);
        Person enhancedPerson = addFieldsToClass(Person.class, fields1);
        System.out.println("新生成的类的属性：");
        for (Field field : enhancedPerson.getClass().getDeclaredFields()) {
            System.out.println(field.getName());
        }

    }

    public static void test(String field1, String field2) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException, IOException {
        // 使用ByteBuddy动态创建增强类
        DynamicType.Unloaded<Person> unloaded = new ByteBuddy()
                .subclass(Person.class)
                .name("PersonNew")
                .defineField(field1, String.class)
                .method(ElementMatchers.named("setNewField"))
                .intercept(MethodDelegation.to(SetterInterceptor.class))
                .method(ElementMatchers.named("getNewField"))
                .intercept(MethodDelegation.to(GetterInterceptor.class))
                .defineField(field2, String.class)
                .method(ElementMatchers.named("setNewField1"))
                .intercept(MethodDelegation.to(SetterInterceptor.class))
                .method(ElementMatchers.named("getNewField1"))
                .intercept(MethodDelegation.to(GetterInterceptor.class)).make();

        DynamicType.Loaded<Person> personLoaded = unloaded.load(MainTest.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER);
        unloaded.saveIn(new File("C:\\Users\\Admin\\Desktop\\testClass\\"));
        Person enhancedPerson = personLoaded.getLoaded().newInstance();
        System.out.println("新生成的类的属性：");
        for (Field field : enhancedPerson.getClass().getDeclaredFields()) {
            System.out.println(field.getName());
        }
    }

    @Data
    public static class Person {
        private String p;

        public Person() {
        }
    }

    public static <T> T addFieldsToClass(Class<T> clazz, Map<String, Class<?>> fieldTypes) {
        DynamicType.Builder<T> builder = new ByteBuddy().subclass(clazz);

        for (Map.Entry<String, Class<?>> entry : fieldTypes.entrySet()) {
            String fieldName = entry.getKey();
            Class<?> fieldType = entry.getValue();

            builder.defineField(fieldName, fieldType, Visibility.PRIVATE)
                    .method(ElementMatchers.named("get" + capitalize(fieldName)))
                    .intercept(FixedValue.value("1"))
                    .method(ElementMatchers.named("set" + capitalize(fieldName)))
                    .intercept(FixedValue.value("1111"));
        }
        Class<? extends T> loaded = builder.make()
                .load(clazz.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();
        T newInstance = null;
        try {
            newInstance = loaded.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return newInstance;
    }

    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    public static class SetterInterceptor {
                public static void setNewField(@AllArguments Object[] args, @SuperCall Callable<?> superCall)
                throws Exception {
            superCall.call();
        }}
        public static class GetterInterceptor {
            @RuntimeType
            public static Object getNewField(@AllArguments Object[] args, @SuperCall Callable<?> superCall)
                    throws Exception {
                return superCall.call();
            }
        }
    }

