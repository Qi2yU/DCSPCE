package com.example.yunnan.mapper;

import com.example.yunnan.entity.user_accounts;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface user_accountsMapper {

    //查询未上报数据
    @Select("select user_id,user_name from user_accounts where user_id not in " +
            "(select user_id from company_info)")
    List<user_accounts> selectfromtwo();
}
