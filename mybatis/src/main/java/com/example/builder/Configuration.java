package com.example.builder;

import com.example.bind.MapperRegistry;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    /**
     * 映射注册机
     */
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 映射的语句，存在Map里
     */
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

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
        mappedStatements.put(ms.getId(), ms);
    }
}
