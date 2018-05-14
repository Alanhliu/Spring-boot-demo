package com.example.demo.controller;

import com.example.demo.annotation.Authorization;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.User;
import com.example.demo.service.RedisService;
import com.example.demo.util.Error;
import com.example.demo.service.UserService;
import com.example.demo.util.R;
import com.example.demo.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;


    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public R login(@RequestParam String username,
                   @RequestParam String password) {

        User user = userService.findUserByName(username);

        if (user == null || !user.password.equals(password)) {
            //返回用户名或密码不正确
            return R.error(Error.USER_NAME_OR_PWD_ERROR,"USER_NAME_OR_PWD_ERROR");
        }

        String token = TokenUtil.CreateToken(user.username,user.uid);

        redisService.set(user.getUid().toString(),token);
        redisService.set(token,user.getUid().toString());

        Map map = new HashMap();
        map.put("uid",user.uid);
        map.put("msg","login success");
        map.put("token",token);
        map.put("expire_date",3600*24*30);

        return R.ok(map);
    }

    @RequestMapping(value = "/list",method = {RequestMethod.GET})
    public R getUsers() {
        List list = userService.findAllUser();
        return R.ok(list);
    }

    @Authorization
    @RequestMapping(value = "/self",method = {RequestMethod.GET})
    public R getUserSelf(HttpServletRequest request) {

        int uid = Integer.parseInt(request.getAttribute("uid").toString());
        User user = userService.findUser(uid);
        if (user == null) {
            throw new NotFoundException(new Error(Error.USER_NOT_FOUND));
        }
        return R.ok(user);
    }

    @RequestMapping(value = "/{id}",method = {RequestMethod.GET})
    public R getUser(@PathVariable Integer id) {

        User user = userService.findUser(id);
        if (user == null) {
            throw new NotFoundException(new Error(Error.USER_NOT_FOUND));
        }
        return R.ok(user);
    }

    @RequestMapping(value = "/add",method = {RequestMethod.POST})
    public R addUser(User user) {
        userService.addUserSelective(user);

        User theUser = userService.findUser(user.uid);

        return R.ok(theUser);
    }

    @RequestMapping(value = "/{id}",method = {RequestMethod.DELETE})
    public R deleteUser(@PathVariable Integer id) {

        User user = userService.findUser(id);
        if (user == null) {
            throw new NotFoundException(new Error(Error.USER_NOT_FOUND));
        } else {
            userService.deleteUser(id);
        }

        Map map = new HashMap();
        map.put("result","success");
        map.put("uid",id);

        return R.ok(map);
    }

    @RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
    public R updateUser(@PathVariable Integer id, User userForm) {

        User user = userService.findUser(id);

        if (user == null) {
            throw new NotFoundException(new Error(Error.USER_NOT_FOUND));
        }

        userForm.setUid(id);

        userService.updateUser(userForm);
        user = userService.findUser(id);


        return R.ok(user);
    }
}
