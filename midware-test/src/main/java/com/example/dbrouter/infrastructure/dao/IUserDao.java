package com.example.dbrouter.infrastructure.dao;

import com.example.dbrouter.annotation.DBRouter;
import com.example.dbrouter.annotation.DS;
import com.example.dbrouter.infrastructure.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserDao {

     @DBRouter(key = "userId")
     User queryUserInfoByUserId(User req);

     @DBRouter(key = "userId")
     void insertUser(User req);

     @DS(key = "db1")
     User getByUserId(User req);

     @DS(key = "db2")
     User getByUserId1(User req);

}
