package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();

    User findUser(Integer uid);

    void deleteUser(Integer uid);

    User updateUser(User user);

    int addUser(User user);

    int addUserSelective(User user);
}
