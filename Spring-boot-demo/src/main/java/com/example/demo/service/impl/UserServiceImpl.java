package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public User findUser(Integer uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    public void deleteUser(Integer uid) {
        userMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public User updateUser(User user) {
        return userMapper.updateByModel(user);
    }
}
