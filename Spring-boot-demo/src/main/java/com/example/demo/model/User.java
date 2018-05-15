package com.example.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User", description = "用户对象")
public class User {
    @ApiModelProperty(value = "ID",hidden = true)
    public Integer uid;
    @ApiModelProperty(value = "姓名",example = "hahaha",position = 0)
    public String username;
    @ApiModelProperty(value = "密码",example = "123456",position = 1)
    public String password;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
