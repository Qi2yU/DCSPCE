//package com.example.yunnan.service;
//import com.example.yunnan.entity.SumEntity;
//import com.example.yunnan.mapper.sum_databyOthers;
//import com.example.yunnan.mapper.sum_databyTime;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//public class sum_databyTime_service {
//    @Autowired
//    private sum_databyTime sum_databyTime;
//
//    private List<String> User_idList;
//    private List<String> Time_idList;
//
//    public void getUserId(String sum_id){
//        User_idList = sum_databyOthers_Mapper.get_user_id(sum_id);
//    }
//    public void getTimeId(String start_time, String end_time){
//        Time_idList = sum_databyOthers_Mapper.get_time_id(start_time, end_time);
//    }
//    public List<SumEntity> getResult(List<String> User_idList, List<String> Time_idList){
//        return  sum_databyOthers_Mapper.get_result(User_idList,Time_idList);
//    }
//}
