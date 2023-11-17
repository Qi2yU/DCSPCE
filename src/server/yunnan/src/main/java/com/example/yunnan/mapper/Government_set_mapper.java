package com.example.yunnan.mapper;

import com.example.yunnan.msg.AddUserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Government_set_mapper {
    void insertNewUser(String userName, String userId, String password, int is_record, int role);

    void insertTableSearch(String id, String rs, String re, String fs, String fe, int is_finish, String index_table);

    void createNewTable(String index_table);
}
