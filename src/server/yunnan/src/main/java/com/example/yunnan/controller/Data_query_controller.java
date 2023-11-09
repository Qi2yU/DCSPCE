package com.example.yunnan.controller;

import com.example.yunnan.entity.QueryDetail;
import com.example.yunnan.entity.query_form;
import com.example.yunnan.entity.query_res1;
import com.example.yunnan.entity.res_time;
import com.example.yunnan.service.Data_query_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class Data_query_controller {
    @Autowired
    private Data_query_service dataQueryService;

    @GetMapping("/test")
    @ResponseBody
    public void getName(String tableName,String userId)
    {
        int state= dataQueryService.getName(tableName,userId);
        System.out.println(state);
    }
    @PostMapping("/dataquery")
    @ResponseBody
    public List<query_res1> getName(@RequestBody query_form data){
        String companyName=data.getCompanyName();
        String userId = data.getUserId();
        String character=data.getCharacter();
        String industry=data.getIndustry();
        String city=data.getCity();
        String district= data.getDistrict();
        String start_time=data.getStart_time();
        String end_time=data.getEnd_time();
        System.out.println(data);
        List<res_time> list= dataQueryService.getTable(start_time,end_time);
        System.out.println(list);
//        String tableName= list.get(0).getTableName();
//        List<query_res1> list_res=dataQueryService.getRes1(tableName,companyName,userId,character,industry,city,district);
//        System.out.println(list_res);
        List<query_res1> list_all=new ArrayList<>();
        for(int i=0;i<list.size();i++)
        {
            String tableName= list.get(i).getTableName();
            List<query_res1> list_res=new ArrayList<>();
            list_res=dataQueryService.getRes1(tableName,companyName,userId,character,industry,city,district);
            System.out.println(list_res);
            for(int j=0;j<list_res.size();j++)
            {
                list_res.get(j).setStart_time(list.get(i).getStart_time());
                list_res.get(j).setEnd_time(list.get(i).getEnd_time());
                list_res.get(j).setTableName(list.get(i).getTableName());

            }
            list_all.addAll(list_res);
            System.out.println(list_all);
        }
        return list_all;


    }

    @GetMapping("/querydetail")
    @ResponseBody
    public QueryDetail getQueryDetail(String tableName,String userId){
        return dataQueryService.getQueryDetail(tableName, userId);
    }


}
