package com.example.yunnan.mapper;

import com.example.yunnan.entity.SumEntity;
import com.example.yunnan.entity.SumMountedEntity;
import com.example.yunnan.entity.TimeidEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface sum_databyTime {


    List<SumEntity> get_data(String timeTableId);
    List<SumEntity> get_dataCity(String timeTableId, String city);
    List<SumEntity> get_datawithpro_city(String timeTableId);
    List<SumEntity> get_datawithpro_char(String timeTableId);
    List<SumEntity> get_datawithpro_charCity(String timeTableId,String city);

    List<SumEntity> get_datawithpro_industry(String timeTableId);

    List<SumEntity> get_datawithpro_industryCity(String timeTableId,String city);
    String get_timeTableId();

    List<SumMountedEntity> get_datamounted(String timeTableId);

    List<SumMountedEntity> get_datamounted_city(String timeTableId, String city);

    Integer get_type(String timeTableId);
    List<TimeidEntity> get_mounted_start();
    List<TimeidEntity> get_mounted_end();





}
