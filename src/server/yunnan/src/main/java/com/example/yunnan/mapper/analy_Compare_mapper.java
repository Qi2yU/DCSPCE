package com.example.yunnan.mapper;

import com.example.yunnan.entity.CompareEntity;
import com.example.yunnan.entity.CompareMountedEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface analy_Compare_mapper {

    List<CompareEntity> get_data(String timeTableId, String industry, String city, String character);
    List<String> get_mounted_city();
    List<String> get_mounted_char();
    List<String> get_mounted_indu();
}
