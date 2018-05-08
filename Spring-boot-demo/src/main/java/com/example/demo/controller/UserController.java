package com.example.demo.controller;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.util.Error;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    private JdbcTemplate jdbcTemplate;


    @Autowired
    private UserService userService;

//    @RequestMapping("/login")
//    public void login() {
//
//    }

//    @RequestMapping("/self")
//    public Map<String, Object> getSelf() {
//        String sql = "select * from USER where id = 1";
//        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
//        Map<String, Object> map = list.get(0);
//        return map;
//    }

//    @RequestMapping("/{id}")
//    public Map<String, Object> getUser(@PathVariable String id) {
//
//        String sql = "select * from USER where id = "+id;
//        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
//        Map<String, Object> map = list.get(0);
//        return map;
//    }

//    @RequestMapping("/list")
//    public List<Map<String, Object>> getUsers(){
//        String sql = "select * from USER";
//        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
//        for (Map<String, Object> map : list) {
//            Set<Map.Entry<String, Object>> entries = map.entrySet( );
//            if(entries != null) {
//                Iterator<Entry<String, Object>> iterator = entries.iterator();
//                while(iterator.hasNext( )) {
//                    Entry<String, Object> entry =(Entry<String, Object>) iterator.next();
//                    Object key = entry.getKey( );
//                    Object value = entry.getValue();
//                    System.out.println(key+":"+value);
//                }
//            }
//        }
//        return list;
//    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error userNotFound(UserNotFoundException e) {
        long uid = e.getUid();
        Error error = new Error(10001,"user " + uid + " not found");
        return error;
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
//        User user = userService.findUser(id);

//        User theUser = userService.updateUser(user);

        return null;
    }
}
