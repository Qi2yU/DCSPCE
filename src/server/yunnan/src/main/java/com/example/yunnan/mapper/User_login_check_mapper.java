package com.example.yunnan.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface User_login_check_mapper {

    String findpasswordById(String userId);
}
