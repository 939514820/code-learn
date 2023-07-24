package com.example.bind;

import com.example.builder.MappedStatement;
import com.example.connector.Connector;
import com.example.session.DefaultSqlSession;
import com.example.session.SqlSession;
import com.example.test.dao.User;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Connection;

public class MapperProxy<T> implements InvocationHandler, Serializable {
    private SqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            // 执行具体的sql
            // 数据库操作
            System.out.println(proxy.getClass() + " " + method.getName() + args);
//            mapperInterface.getName();

            DefaultSqlSession session = (DefaultSqlSession) sqlSession;
            MappedStatement statement = session.getMapperRegistry().getMappedStatements().get(method.getName());
            ;

            String sql = buildRealSQl(statement, method, args);

            Connector connector = new Connector();
            connector.getConnection();
            User execute = connector.execute(sql);
            System.out.println("查询结果：" + execute.toString());
            return "你的被代理了！";
        }
    }

    private String buildRealSQl(MappedStatement statement, Method method, Object[] args) {
        String sql = statement.getSql();
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            System.out.println("参数名：" + parameter.getName());
        }

        for (Object arg : args) {
            Class<?> aClass = arg.getClass();
            System.out.println("class:" + aClass + "value:" + arg);
            if (sql.contains("#")) {
                sql = sql.replace("#{id}", String.valueOf(arg));
            }
        }
        System.out.println(sql);
        return sql;
    }
}
