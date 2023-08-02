package com.example.builder;

import com.example.session.Configuration;
import com.example.session.defaults.DefaultSqlSessionFactory;
import com.example.session.SqlSessionFactory;

import java.io.File;
import java.io.Reader;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    private SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }

}
