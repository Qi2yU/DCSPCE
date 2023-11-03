package com.example.yunnan.mapper;

import com.example.yunnan.entity.DataExam;
import com.example.yunnan.entity.detaildata;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Detail_mapper {
    detaildata getDetailById(String userId);
    List<DataExam> getCountByState();
}
