package com.example.yunnan.controller;

import com.example.yunnan.entity.DataExam;
import com.example.yunnan.entity.detaildata;
import com.example.yunnan.service.Detail_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class committed_controller {
    @Autowired
    private Detail_service detailService;
    @GetMapping("/detail")
    @ResponseBody
    public detaildata getDetail(String userID){
        detaildata data=new detaildata();
        data=detailService.getDetailById(userID);

        System.out.println(data);
        return data;
    }
    @GetMapping("/count")
    @ResponseBody
    public List<DataExam> getCount(){
        List<DataExam> list=detailService.getCountByState();
        System.out.println(list);
        return list;
    }
}
