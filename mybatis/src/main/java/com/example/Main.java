package com.example;

import com.example.bind.MapperRegistry;
import com.example.builder.SqlSessionFactoryBuilder;
import com.example.session.DefaultSqlSessionFactory;
import com.example.session.SqlSession;
import com.example.session.SqlSessionFactory;
import com.example.dao.IUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;

public class Main {
    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
//        https://github.com/fuzhengwei/CodeGuide
//        System.out.println("Hello world!");
//        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
//        Map<String, String> sqlSession = new HashMap<>();
//        sqlSession.put("com.example.test.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
//        sqlSession.put("com.example.test.IUserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");
//        IUserDao iUserDao = factory.newInstance(sqlSession);
//        String zhangsan = iUserDao.queryUserName("zhangsan");
//        System.out.println(zhangsan);

        // 1. 注册 Mapper
//        MapperRegistry registry = new MapperRegistry();
//        registry.addMappers("com.example.dao");
//        // 从配置文件读取
//        // 2. 从 SqlSession 工厂获取 Session
//        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
// 1. 从SqlSessionFactory中获取SqlSession
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 4. 测试验证
        String res = userDao.queryUserInfoById("10001");
        log.info("测试结果：{}", res);
    }
}