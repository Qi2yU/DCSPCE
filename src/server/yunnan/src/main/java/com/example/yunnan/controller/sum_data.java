package com.example.yunnan.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.yunnan.entity.SumEntity;
import java.util.List;

@RestController


public class sum_data {


    @GetMapping("/sum/{sum_id}/{start_time}/{end_time}")
    @ResponseBody
    public void get_sum_id_others(@PathVariable String sum_id, @PathVariable String start_time, @PathVariable String end_time){

    }

    @GetMapping("/sum/{sum_id}")
    @ResponseBody
    public void get_sum_id_time(@PathVariable String sum_id){

    }
}
