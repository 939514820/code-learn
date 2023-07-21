package com.example.builder;

import com.example.session.DefaultSqlSessionFactory;
import com.example.session.SqlSessionFactory;

import java.io.Reader;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }

}
