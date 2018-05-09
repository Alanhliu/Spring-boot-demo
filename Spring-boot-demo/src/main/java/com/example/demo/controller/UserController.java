package com.example.demo.controller;

import com.example.demo.exception.UserException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.util.Error;
import com.example.demo.service.UserService;
import com.example.demo.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error userNotFound(UserNotFoundException e) {
        long uid = e.getUid();
        Error error = new Error(10001,"user " + uid + " not found");
        return error;
    }

    @ExceptionHandler(UserException.class)
    public Error userException(UserException e) {
        return e.error;
    }

    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public Map login(@RequestParam String username,
                     @RequestParam String password) {

        User user = userService.findUserByName(username);

        if (user == null || !user.password.equals(password)) {
            //返回用户名或密码不正确
            Error error = new Error(Error.USER_NAME_OR_PWD_ERROR,"username or pwd error");
            throw new UserException(error);
        }

        String token = TokenUtil.CreateToken(user.username,user.uid);

        Map map = new HashMap();
        map.put("uid",user.uid);
        map.put("msg","login success");
        map.put("token",token);
        map.put("expire_date",3600*24*30);

        return map;
    }

    @RequestMapping(value = "/list",method = {RequestMethod.GET})
    public List<User> getUsers() {
        List list = userService.findAllUser();
        return list;
    }

    @RequestMapping(value = "/{id}",method = {RequestMethod.GET})
    public User getUser(@PathVariable Integer id) {

        User user = userService.findUser(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    @RequestMapping(value = "/{id}",method = {RequestMethod.DELETE})
    public Map deleteUser(@PathVariable Integer id) {

        User user = userService.findUser(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        } else {
            userService.deleteUser(id);
        }
        Map map = new HashMap();
        map.put("result","success");
        map.put("uid",id);
        return map;
    }

    @RequestMapping(value = "/add",method = {RequestMethod.POST})
    public User addUser(User user) {
        userService.addUserSelective(user);

        User theUser = userService.findUser(user.uid);

        return theUser;
    }

    @ModelAttribute
    @RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
    public User updateUser(@PathVariable Integer id, User user) {

        user.uid = id;
        userService.updateUser(user);

        User theUser = userService.findUser(id);

        return theUser;
    }
}
