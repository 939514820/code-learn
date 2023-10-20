package com.example.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMapper extends BaseMapper<User> {


}
