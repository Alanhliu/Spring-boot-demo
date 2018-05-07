package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/list",method = {RequestMethod.GET})
    public List<User> getUsers() {
        List list = userService.findAllUser();
        return list;
    }

    @RequestMapping(value = "/{id}",method = {RequestMethod.GET})
    public User getUser(@PathVariable Integer id) {

        User user = userService.findUser(id);
        return user;
    }

    @RequestMapping(value = "/{id}",method = {RequestMethod.DELETE})
    public void deleteUser(@PathVariable Integer id) {

        userService.deleteUser(id);
    }

    @ModelAttribute
    @RequestMapping(value = "/{id}",method = {RequestMethod.PUT})
    public User updateUser(@PathVariable Integer id, User user) {
//        User user = userService.findUser(id);

//        User theUser = userService.updateUser(user);
        
        return null;
    }
}
