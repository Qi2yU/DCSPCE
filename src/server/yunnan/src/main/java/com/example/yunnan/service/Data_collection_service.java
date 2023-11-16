package com.example.yunnan.service;

import com.example.yunnan.mapper.Data_collection_mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Data_collection_service {

    @Autowired
    private Data_collection_mapper dataCollectionMapper;

    public void test_companydata(String companyID,
                                 int docEmploymentNumber,
                                 int curEmploymentNumber,
                                 int numDecreasedReason,
                                 String mainReason,
                                 String secondReason,
                                 String reasonDetail,
                                 int status) {
        dataCollectionMapper.test_companydata(companyID,
                docEmploymentNumber,
                curEmploymentNumber,
                numDecreasedReason,
                mainReason,
                secondReason,
                reasonDetail,
                status);
    }
}