package com.example.yunnan.mapper;

import com.example.yunnan.entity.SumEntity;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface sum_databyOthers {
    @Select("find_user_bysum_id")
    List<String> get_user_id(String sum_id);
    @Select("find_time_id")
    List<String> get_time_id(String start_time, String end_time);

    List<SumEntity> get_result(List<String> user_id, List<String> time_id);

}
