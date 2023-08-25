package com.example.dbrouter.interfaces;

import com.example.dbrouter.infrastructure.dao.IUserDao;
import com.example.dbrouter.infrastructure.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
@RestController
public class DBUserController {

    private Logger logger = LoggerFactory.getLogger(DBUserController.class);

    @Resource
    private IUserDao userDao;

    @RequestMapping(path = "/api/queryUserInfoById", method = RequestMethod.GET)
    public User queryUserInfoById() {
        return userDao.queryUserInfoByUserId(new User("980765512"));
    }

    @RequestMapping(path = "/api/getByUserId", method = RequestMethod.GET)
    public User queryUserInfo(String userId, String db) {
        if (db.equals("db_01")) {
            return userDao.getByUserId(new User(userId));
        } else {
            return userDao.getByUserId1(new User(userId));
        }


    }

}
