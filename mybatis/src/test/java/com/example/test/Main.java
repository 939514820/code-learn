package com.example.test;

import com.example.builder.SqlSessionFactoryBuilder;
import com.example.session.SqlSession;
import com.example.session.SqlSessionFactory;
import com.example.test.dao.IUserDao;
import com.example.test.dao.User;
import com.example.util.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Main {
    private static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
//        https://github.com/fuzhengwei/CodeGuide
//        System.out.println("Hello world!");
//        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
//        Map<String, String> sqlSession = new HashMap<>();
//        sqlSession.put("com.example.test.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
//        sqlSession.put("com.example.test.dao.IUserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");
//        IUserDao iUserDao = factory.newInstance(sqlSession);
//        String zhangsan = iUserDao.queryUserName("zhangsan");
//        System.out.println(zhangsan);

        // 1. 注册 Mapper

// 1. 从SqlSessionFactory中获取SqlSession
//        File reader = new File("D:\\IdeaProjects\\code-learn\\mybatis\\src\\test\\java\\resources\\mapper\\User.xml");
        File reader = new File("D:\\IdeaProjects\\code-learn\\mybatis\\src\\test\\java\\resources\\mappers.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 4. 测试验证
        User res = userDao.queryUserInfoById(1L);

        log.info("测试结果：{}", res);

//        User user = new User();
//        try {
//            user.getClass().getMethod("setId", new Integer(1).getClass());
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
    }

}