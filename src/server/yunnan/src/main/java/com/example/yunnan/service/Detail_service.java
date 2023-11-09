package com.example.yunnan.service;

import com.example.yunnan.entity.*;
import com.example.yunnan.mapper.Detail_mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Detail_service {

    @Autowired
    private Detail_mapper detailMapper;

    public res_time getTable() {return detailMapper.getTable();}

    public detaildata getDetailById(String tableName,String userId) {return detailMapper.getDetailById(tableName,userId);}

    public List<DataExam> getCountByState(String tableName){return detailMapper.getCountByState(tableName);}

    public List<Committed> getCommittedOnCity(String tableName,String city) {return detailMapper.getCommittedOnCity(tableName,city);}

    public List<Retreat> getRetreat(String tableName,String city) {return detailMapper.getRetreat(tableName,city);}

    public List<Uncommitted> getUncommitted(String tableName,String city) {return detailMapper.getUncommitted(tableName,city);}

    public void passByPro(String tableName,String userId) { detailMapper.passByPro(tableName,userId);}

    public void singleUpByPro(String tableName,String userId) {detailMapper.singleUpByPro(tableName,userId);}

    public void batchUpByPro(String tableName,String city) {detailMapper.batchUpByPro(tableName,city);}

    public void retreatByPro(String tableName,String userId) {detailMapper.retreatByPro(tableName,userId);}


//    市端
    public List<DataExam> getCountOnCity(String tableName,String city) {return detailMapper.getCountOnCity(tableName,city);}

    public List<Uncommitted> getUncommittedOnCity(String tableName,String city) {return detailMapper.getUncommittedOnCity(tableName,city);}

    public List<Committed> getCommittedOnCity2(String tableName,String city) {return detailMapper.getCommittedOnCity2(tableName,city);}

    public void passByCity(String tableName,String userId) { detailMapper.passByCity(tableName,userId);}

    public void singleUpByCity(String tableName,String userId) {detailMapper.singleUpByCity(tableName,userId);}

    public void batchUpByCity(String tableName,String city) {detailMapper.batchUpByCity(tableName,city);}



}
