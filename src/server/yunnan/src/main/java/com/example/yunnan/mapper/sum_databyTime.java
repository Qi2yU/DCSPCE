package com.example.yunnan.mapper;

import com.example.yunnan.entity.SumEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface sum_databyTime {


    List<SumEntity> get_data(String timeTableId);

    List<SumEntity> get_datawithpro_city(String timeTableId);
    List<SumEntity> get_datawithpro_char(String timeTableId);
    List<SumEntity> get_datawithpro_industry(String timeTableId);

}
