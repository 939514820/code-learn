import cn.bugstack.mybatis.datasource.pooled.PooledDataSource;
import cn.bugstack.mybatis.io.Resources;
import cn.bugstack.mybatis.session.SqlSession;
import cn.bugstack.mybatis.session.SqlSessionFactory;
import cn.bugstack.mybatis.session.SqlSessionFactoryBuilder;

import com.example.dao.IUserDao;
import com.example.dao.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

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
        User user = new User();
        user.setId(2);
        user.setAge(111);
        user.setName("zhangsan");
//        int res1 = userDao.insert(user);
//        Integer res2 = userDao.queryUserInfo1(user);

//        log.info("测试结果：{}", res);
        log.info("测试结果：{}", res);
//        log.info("测试结果：{}", res2);
        System.out.println("=============");
    }

    @Test
    public void test_pooled() throws SQLException, InterruptedException {
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver("com.mysql.cj.jdbc.Driver");
        pooledDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true");
        pooledDataSource.setUsername("root");
        pooledDataSource.setPassword("123456");
        // 持续获得链接
        while (true) {
            Connection connection = pooledDataSource.getConnection();
            System.out.println(connection);
            Thread.sleep(1000);
            // 注释掉/不注释掉测试
            connection.close();
        }
    }
}