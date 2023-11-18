package com.example.yunnan.mapper;

import com.example.yunnan.entity.comInfoRefused;
import com.example.yunnan.entity.empInfoRefused;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface refused_info_mapper {

//    List<comInfoRefused> getComInfoRefused()
    List<empInfoRefused> getEmpInfoRefused(String tableName, String userId);


}
