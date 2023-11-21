package com.example.yunnan.service;

import com.example.yunnan.controller.government_set;
import com.example.yunnan.entity.UserInfo_set_entity;
import com.example.yunnan.entity.is_end_entity;
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

    public List<is_end_entity> schedule_notend() {
        return Government_Set_Mapper.schedule_notend();
    }

    public void change_finish(String id) {
        Government_Set_Mapper.change_finish(id);
    }

    public List<String> get_companyList() {
        return Government_Set_Mapper.get_companyList();
    }

    public Integer get_lastnum(String id, String table) {
        return Government_Set_Mapper.get_lastnum(id, table);
    }

    public void insertNum(String id, int num, int state, String table) {
        Government_Set_Mapper.insertNum(id, num, state, table);
    }

    public void dropTable(String index_table) {
        System.out.println("删除相同表");
        Government_Set_Mapper.dropTable(index_table);
    }
}
