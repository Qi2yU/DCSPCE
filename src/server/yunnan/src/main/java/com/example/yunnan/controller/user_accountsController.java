package com.example.yunnan.controller;


import com.example.yunnan.entity.user_accounts;
import com.example.yunnan.mapper.user_accountsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class user_accountsController {

    @Autowired
    private user_accountsMapper user_accountsMapper;

    //查询未上报数据
    @RequestMapping("/user_accounts/multi")
    public List<user_accounts> find()
    {
        return user_accountsMapper.selectfromtwo();
    }
}
