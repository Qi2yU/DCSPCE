package com.example.yunnan.controller;

import com.example.yunnan.entity.*;
import com.example.yunnan.service.Data_query_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;

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

    @PostMapping("/query2")
    @ResponseBody
    public List<QueryDetail> getQuery(@RequestBody query_form2 data){
        String companyName=data.getCompanyName();
        String userId = data.getUserId();
        String character=data.getCharacter();
        String industry=data.getIndustry();
        String city=data.getCity();
        String district= data.getDistrict();
        String date1=data.getStart_date();
        String date2=data.getEnd_date();
        System.out.println(date1);
        // 定义输入日期字符串的格式
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM");

        // 定义输出日期字符串的格式
        SimpleDateFormat outputFormat = new SimpleDateFormat("'data'_yyyy_MM_0");

        try {
            // 直接使用输入字符串创建Date对象
            Date date_1 = inputFormat.parse(date1);
            Date date_2 = inputFormat.parse(date2);
            // 格式化日期并输出
            String start_date = outputFormat.format(date_1);
            String end_date = outputFormat.format(date_2);
            List<res_time> list= dataQueryService.getTableByMonth(start_date,end_date);
            System.out.println(list);
//        String tableName= list.get(0).getTableName();
//        List<query_res1> list_res=dataQueryService.getRes1(tableName,companyName,userId,character,industry,city,district);
//        System.out.println(list_res);
            List<QueryDetail> list_all=new ArrayList<>();
            for(int i=0;i<list.size();i++)
            {
                String tableName= list.get(i).getTableName();
                List<QueryDetail> list_res=new ArrayList<>();
                list_res=dataQueryService.getRes1(tableName,companyName,userId,character,industry,city,district);
                System.out.println(list_res);
                for(int j=0;j<list_res.size();j++)
                {
                    list_res.get(j).setStart_time(list.get(i).getStart_time());
                    list_res.get(j).setEnd_time(list.get(i).getEnd_time());


                }
                list_all.addAll(list_res);
                System.out.println(list_all);
            }
            return list_all;

        } catch (ParseException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    @PostMapping("/dataquery")
    @ResponseBody
    public List<QueryDetail> getName(@RequestBody query_form data){
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
        List<QueryDetail> list_all=new ArrayList<>();
        for(int i=0;i<list.size();i++)
        {
            String tableName= list.get(i).getTableName();
            List<QueryDetail> list_res=new ArrayList<>();
            list_res=dataQueryService.getRes1(tableName,companyName,userId,character,industry,city,district);
            System.out.println(list_res);
            for(int j=0;j<list_res.size();j++)
            {
                list_res.get(j).setStart_time(list.get(i).getStart_time());
                list_res.get(j).setEnd_time(list.get(i).getEnd_time());


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
