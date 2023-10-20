package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.IUserInfoMapper;
import com.example.dao.IUserMapper;
import com.example.dto.User;
import com.example.dto.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private IUserInfoMapper userDao;

    /**
     * @return
     */
    @GetMapping("/test1")
    public Object queryUserInfo() {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserInfo::getUserId, 2);
        return userDao.selectOne(queryWrapper);
    }
}
