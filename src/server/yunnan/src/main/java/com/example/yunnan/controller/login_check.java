package com.example.yunnan.controller;

import com.example.yunnan.msg.User_login_info;
import com.example.yunnan.service.User_login_check_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class login_check {

    @Autowired
    private User_login_check_service userLoginCheckService;

    /*
    *下面是Post方法的示例
    * 测试的时候得用到apifox，在没有使用前端时
    * 这个地方默认前端传的json格式的数据 */
    @PostMapping("/login")
    @ResponseBody
    public String checklogin(@RequestBody User_login_info userLoginInfo) {
        System.out.println(userLoginInfo.getUserId());
        System.out.println(userLoginInfo.getPassword());
        String sql_password;
        sql_password = userLoginCheckService.findpasswordById(userLoginInfo.getUserId());
        if(Objects.equals(sql_password, userLoginInfo.getPassword())) {
            return "success";
        } else {
            return "false";
        }
    }

    /*
    *下面是Get方法的示例 */
    @GetMapping("/get_login/{userId}/{password}")
    @ResponseBody
    public String get_checklogin(@PathVariable String userId, @PathVariable String password) {
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
