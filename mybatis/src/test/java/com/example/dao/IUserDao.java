package com.example.dao;

public interface IUserDao {

    User queryUserInfoById(Long id);

    User queryUserInfoById(User user);

}
