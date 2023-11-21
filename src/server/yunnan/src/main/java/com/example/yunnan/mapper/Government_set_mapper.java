package com.example.yunnan.mapper;

import com.example.yunnan.entity.UserInfo_set_entity;
import com.example.yunnan.entity.is_end_entity;
import com.example.yunnan.entity.searchtableInfo_entity;
import com.example.yunnan.msg.AddUserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Government_set_mapper {
    void insertNewUser(String userName, String userId, String password, int is_record, int role);

    void insertTableSearch(String id, String rs, String re, String fs, String fe, int type, int is_finish, String index_table);

    void createNewTable(String index_table);

    List<UserInfo_set_entity> findalluser();

    List<searchtableInfo_entity> findalltable();

    List<is_end_entity> schedule_notend();

    void change_finish(String id);

    List<String> get_companyList();

    Integer get_lastnum(String user_id, String table);

    void insertNum(String user_id, int last_num, int state, String table);
}
