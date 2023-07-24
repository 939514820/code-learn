package com.example.builder;

import com.example.bind.MapperRegistry;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

//    /**
//     * 映射注册机
//     */
    public MapperRegistry mapperRegistry = new MapperRegistry();



    /**
     * 存储代理类对象
     * @param type
     * @param <T>
     */
    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    /**
     * 存储sql语句
     * @param ms
     */
    public void addMappedStatement(MappedStatement ms) {
        mapperRegistry.getMappedStatements().put(ms.getId(), ms);
    }
}
