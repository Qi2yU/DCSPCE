package com.example.yunnan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class test_linkto_mysql {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/test_link_mysql")
    @ResponseBody
    public List<Map<String, Object>> getInfo() {
        String sql = "select * from user_accounts";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
}
