package com.example.dao;

public interface IUserDao {

    User queryUserInfoById(Long id);

    User queryUserInfo(User user);

}
