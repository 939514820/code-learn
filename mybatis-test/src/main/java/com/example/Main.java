package com.example;

import com.example.builder.SqlSessionFactoryBuilder;
import com.example.dao.IUserDao;
import com.example.dao.User;
import com.example.io.Resources;
import com.example.session.SqlSession;
import com.example.session.SqlSessionFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
//        File reader = new File("D:\\IdeaProjects\\code-learn\\mybatis-test\\src\\main\\resources\\mybatis-config.xml");
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 4. 测试验证
        User res = userDao.queryUserInfoById(1L);

        log.info("测试结果：{}", res);

    }
}