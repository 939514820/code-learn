package com.example.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.User;
import com.example.dto.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserInfoMapper extends BaseMapper<UserInfo> {


}
