package com.example.yunnan.service;

import com.example.yunnan.entity.DataExam;
import com.example.yunnan.entity.detaildata;
import com.example.yunnan.mapper.Detail_mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Detail_service {

    @Autowired
    private Detail_mapper detailMapper;
    public detaildata getDetailById(String userId) {return detailMapper.getDetailById(userId);}

    public List<DataExam> getCountByState(){return detailMapper.getCountByState();}
}
