package com.example.yunnan.controller;

import com.example.yunnan.service.User_login_check_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class login_check {

    @Autowired
    private User_login_check_service userLoginCheckService;
    @GetMapping("/login/{userId}/{password}")
    @ResponseBody
    public String checklogin(@PathVariable String userId, @PathVariable String password) {
        String sql_password;
        System.out.println(userId);
        System.out.println(password);
        sql_password = userLoginCheckService.findpasswordById(userId);
        if(Objects.equals(sql_password, password)) {
            return "密码正确";
        } else {
            return "密码错误";
        }
    }
}
