package com.example.yunnan.service;

import com.example.yunnan.entity.UserInfo;
import com.example.yunnan.mapper.Recorded_info_mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Recorded_info_service {
    @Autowired
    private Recorded_info_mapper recordedInfoMapper;

    public List<UserInfo> getRecorded_info(String userId){
        return recordedInfoMapper.getRecorded_info(userId);
    }
}
