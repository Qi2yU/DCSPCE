package com.example.yunnan.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface analy_Sample {
     List<Map<String, String>>  get_data();
}
