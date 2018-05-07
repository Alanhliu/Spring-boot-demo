package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectAllUser();

    User selectByPrimaryKey(Integer uid);

    void deleteByPrimaryKey(Integer uid);

    User updateByModel(User user);
}
