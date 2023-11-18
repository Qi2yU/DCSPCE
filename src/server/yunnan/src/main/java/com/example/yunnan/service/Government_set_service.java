package com.example.yunnan.service;

import com.example.yunnan.entity.UserInfo_set_entity;
import com.example.yunnan.entity.searchtableInfo_entity;
import com.example.yunnan.mapper.Government_set_mapper;
import com.example.yunnan.msg.AddUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Government_set_service {

    @Autowired
    private Government_set_mapper Government_Set_Mapper;

    public void insertNewUser(String userName, String userId, String password, int is_record, int role) {
        Government_Set_Mapper.insertNewUser(userName, userId, password, is_record, role);
    }

    public void insertNewTableSearch(String id, String rs, String re, String fs, String fe, int type, int is_finish, String index_table) {
        Government_Set_Mapper.insertTableSearch(id, rs, re, fs, fe, type, is_finish, index_table);
    }

    public void createNewTable(String index_table) {
        Government_Set_Mapper.createNewTable(index_table);
    }

    public List<UserInfo_set_entity> find_all_user() {
        return Government_Set_Mapper.findalluser();
    }

    public List<searchtableInfo_entity> findalltable() {
        return Government_Set_Mapper.findalltable();
    }
}
