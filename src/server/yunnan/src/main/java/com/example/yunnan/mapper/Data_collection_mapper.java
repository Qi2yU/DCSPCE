package com.example.yunnan.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Data_collection_mapper {

    void test_companydata(String tableName,
                          String companyID,
                          int docEmploymentNumber,
                          int curEmploymentNumber,
                          String numDecreasedReason,
                          String mainReason,
                          String secondReason,
                          String reasonDetail,
                          int status);
}
