package com.example.yunnan.service;

import com.example.yunnan.entity.QueryDetail;
import com.example.yunnan.entity.query_res1;
import com.example.yunnan.entity.res_time;
import com.example.yunnan.mapper.Data_query_mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Data_query_service {
    @Autowired
    private Data_query_mapper dataQueryMapper;

    public int getName(String tableName, String userId) {return dataQueryMapper.getName(tableName,userId);}

    public List<res_time> getTable(String start_time, String end_time) {return dataQueryMapper.getTable(start_time, end_time);}

    public List<QueryDetail> getRes1(String tableName,String companyName,String userId,String character,String industry, String city, String district)
    {
        return dataQueryMapper.getRes1(tableName,companyName, userId, character, industry, city, district);
    }

    public QueryDetail getQueryDetail(String tableName,String userId) {return dataQueryMapper.getQueryDetail(tableName, userId);}

    public List<res_time> getTableByMonth(String start_date, String end_date) {return dataQueryMapper.getTableByMonth(start_date, end_date);}
}
