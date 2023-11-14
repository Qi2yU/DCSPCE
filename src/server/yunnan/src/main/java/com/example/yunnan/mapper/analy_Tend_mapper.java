package com.example.yunnan.mapper;

import com.example.yunnan.entity.TendEntity;
import com.example.yunnan.entity.TendResEntity;
import org.apache.ibatis.annotations.Mapper;
import org.w3c.dom.ls.LSException;

import java.util.List;

@Mapper
public interface analy_Tend_mapper {

    List<TendEntity> get_data(String timeTableId,String industry, String city, String character);
    List<TendResEntity> get_resname(String industry, String city, String character);
    List<String> get_mounted_city();
    List<String> get_mounted_char();
    List<String> get_mounted_indu();
}
