package com.example.yunnan.mapper;

import com.example.yunnan.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Detail_mapper {

    res_time getTable();
    detaildata getDetailById(String tableName,String userId);
    List<DataExam> getCountByState(String tableName);

    List<Committed> getCommittedOnCity(String tableName,String city);

    List<Retreat> getRetreat(String tableName,String city);

    List<Uncommitted> getUncommitted(String tableName,String city);

    void passByPro(String tableName,String userId);

    void singleUpByPro(String tableName,String userId);

    void batchUpByPro(String tableName,String city);

    void retreatByPro(String tableName,String userId);

//    市端
    List<DataExam> getCountOnCity(String tableName,String city);

    List<Uncommitted> getUncommittedOnCity(String tableName,String city);

    List<Committed> getCommittedOnCity2(String tableName,String city);

    void passByCity(String tableName,String userId);

    void singleUpByCity(String tableName,String userId);

    void batchUpByCity(String tableName,String city);

}
