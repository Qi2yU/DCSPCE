package com.example.yunnan.service;

import com.example.yunnan.entity.empInfoRefused;
import com.example.yunnan.mapper.Refused_info_mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Refused_info_service {
    @Autowired
    private Refused_info_mapper refusedinfomapper;

    public List<empInfoRefused> getEmpInfoRefused(String tableName, String userId) {
        return refusedinfomapper.getEmpInfoRefused(tableName, userId);
    }
}
