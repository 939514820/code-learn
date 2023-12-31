import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.Application;
import com.example.dao.IUserInfoMapper;
import com.example.dao.IUserMapper;
import com.example.dto.User;
import com.example.dto.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestCut {
    @Resource
    private IUserMapper userDao;
    @Resource
    private IUserInfoMapper userInfoMapper;

    @Test
    public void test() {
        // 测试分库分表
//        for (int i = 1; i < 50; i++) {
//            User user = new User();
//            user.setUserId(i);
//            user.setUserNickName("");
//            user.setUserHead("head");
//            user.setUserPassword("1");
//            user.setCreateTime(new Date());
//            user.setUpdateTime(new Date());
//            userDao.insert(user);
//        }
        // 测试读写分离
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserInfo::getUserId, 2);
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        System.out.println(userInfo);
        UserInfo user = new UserInfo();
        user.setUserId(55);
        user.setUserNickName("");
        user.setUserHead("headinsert");
        user.setUserPassword("1");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userInfoMapper.insert(user);
    }
}
