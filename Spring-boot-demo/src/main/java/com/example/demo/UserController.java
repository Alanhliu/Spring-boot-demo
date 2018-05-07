package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/login")
    public void login() {

    }

    @RequestMapping("/self")
    public Map<String, Object> getSelf() {
        String sql = "select * from USER where id = 1";
        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
        Map<String, Object> map = list.get(0);
        return map;
    }

    @RequestMapping("/{id}")
    public Map<String, Object> getUser(@PathVariable String id) {

        String sql = "select * from USER where id = "+id;
        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
        Map<String, Object> map = list.get(0);
        return map;
    }

    @RequestMapping("/list")
    public List<Map<String, Object>> getUsers(){
        String sql = "select * from USER";
        List<Map<String, Object>> list =  jdbcTemplate.queryForList(sql);
        for (Map<String, Object> map : list) {
            Set<Map.Entry<String, Object>> entries = map.entrySet( );
            if(entries != null) {
                Iterator<Entry<String, Object>> iterator = entries.iterator();
                while(iterator.hasNext( )) {
                    Entry<String, Object> entry =(Entry<String, Object>) iterator.next();
                    Object key = entry.getKey( );
                    Object value = entry.getValue();
                    System.out.println(key+":"+value);
                }
            }
        }
        return list;
    }

    public void delete() {

    }
}
