package com.example.yunnan.mapper;

import com.example.yunnan.entity.QueryDetail;
import com.example.yunnan.entity.query_res1;
import com.example.yunnan.entity.res_time;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Data_query_mapper {
    int getName(@Param("tableName") String tableName, @Param("userId") String userId);

    List<res_time> getTable(String start_time, String end_time);

    List<QueryDetail> getRes1(String tableName,String companyName,String userId,String character,String industry, String city, String district);

    QueryDetail getQueryDetail(String tableName,String userId);

    List<res_time> getTableByMonth(String start_date, String end_date);
}
