package com.example.dao;

public interface IUserDao {

    User queryUserInfoById(Long id);

    User queryUserInfo(User user);
    int insert(User user);
    Integer queryUserInfo1(User user);

}
