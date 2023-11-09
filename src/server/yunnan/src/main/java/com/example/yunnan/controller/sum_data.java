package com.example.yunnan.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.yunnan.entity.SumEntity;
import com.example.yunnan.service.sum_databyTime_service;
import java.util.List;

@RestController


public class sum_data {

    private sum_databyTime_service Servic;
    @GetMapping("/sum/{sum_id}/{start_time}/{end_time}")
    @ResponseBody
    public void get_sum(@PathVariable String sum_id, @PathVariable String start_time, @PathVariable String end_time){
        if(sum_id =="企业性质"){

        }
        else if(sum_id == "调查期"){
            start_time = "2023_9_0";
            end_time = "2023_9_1";

        }

    }

}
