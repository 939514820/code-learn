package com.example;

import com.example.bind.MapperProxyFactory;
import com.example.test.IUserDao;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        https://github.com/fuzhengwei/CodeGuide
//        System.out.println("Hello world!");
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
        Map<String, String> sqlSession = new HashMap<>();
        sqlSession.put("com.example.test.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        sqlSession.put("com.example.test.IUserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");
        IUserDao iUserDao = factory.newInstance(sqlSession);
        String zhangsan = iUserDao.queryUserName("zhangsan");
        System.out.println(zhangsan);
    }
}