package com.example.yunnan.entity;

import com.example.yunnan.msg.User_login_info;

public class UserInfo_set_entity {
    private String user_name;
    private String user_id;
    private String password;

    public UserInfo_set_entity(String id, String name, String password) {
        this.user_name=name;
        this.user_id=id;
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }
}
