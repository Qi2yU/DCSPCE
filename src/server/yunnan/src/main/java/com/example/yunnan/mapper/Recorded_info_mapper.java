package com.example.yunnan.mapper;


import com.example.yunnan.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Recorded_info_mapper {
    List<UserInfo> getRecorded_info(String userId);
}
