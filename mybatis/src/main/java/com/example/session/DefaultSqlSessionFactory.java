package com.example.session;

import com.example.bind.MapperRegistry;
import com.example.builder.Configuration;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(Configuration configuration) {
        mapperRegistry = new MapperRegistry(configuration);

    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }

}