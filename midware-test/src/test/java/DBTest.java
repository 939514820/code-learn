import com.example.Main;
import com.example.dbrouter.infrastructure.dao.IUserDao;
import com.example.dbrouter.infrastructure.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class DBTest {
    @Resource
    private IUserDao userDao;

    /**
     * 自动路由
     *
     * @return
     */
    @Test
    public void queryUserInfoById() {
        System.out.println(userDao.queryUserInfoByUserId(new User("12345")));
        System.out.println(userDao.queryUserInfoByUserId(new User("2")));
    }

    /**
     * @param db @DS测试
     * @return
     */
    @Test
    public void queryUserInfo() {

        System.out.println("db1:" + userDao.getByUserId(new User("1")));
        System.out.println("db2:" + userDao.getByUserId1(new User("2")));
    }
}
