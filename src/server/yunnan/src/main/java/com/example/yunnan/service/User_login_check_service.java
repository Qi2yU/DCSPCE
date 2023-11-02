package com.example.yunnan.service;

import com.example.yunnan.entity.User_login_check;
import com.example.yunnan.mapper.User_login_check_mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User_login_check_service {

    @Autowired
    private User_login_check_mapper userLoginCheckMapper;

    public String findpasswordById(String userId) {
        return userLoginCheckMapper.findpasswordById(userId);
    }
}
