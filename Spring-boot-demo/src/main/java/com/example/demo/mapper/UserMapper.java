package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectAllUser();

    User selectByPrimaryKey(Integer uid);

    User selectByUserName(String username);

    void deleteByPrimaryKey(Integer uid);

    int updateByModel(User user);

    int insert(User user);

    int insertSelective(User user);
}
