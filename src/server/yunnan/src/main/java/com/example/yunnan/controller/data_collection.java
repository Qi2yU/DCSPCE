package com.example.yunnan.controller;


import com.example.yunnan.entity.EmploymentDataEntry;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin
public class data_collection {


    @RequestMapping(value="/data_collection",method= RequestMethod.POST)
    public String collect_data(@RequestBody  EmploymentDataEntry ede){
        // 当期上报的就业数据写入数据库
        System.out.println(ede.getCompanyID() + ede.getDocEmploymentNumber() + ede.getCurEmploymentNumber());
        // 写入DB
        return "Hello SpringBoot!";
    }

    @RequestMapping(value="/get_company_collection_data",method= RequestMethod.GET)
    public String get_collection_data(String cid) throws JsonProcessingException {
        // 企业获取当期上报的就业数据，get需要提供企业cid

        // 从DB读入
        // getCompanyEmploymentData(cid);

        // 这里先抓一个默认的数据
        EmploymentDataEntry ede = new EmploymentDataEntry();
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("返回了默认的就业数据");
        // 返回为json格式
        return objectMapper.writeValueAsString(ede.get_default_EDE(3));
    }
}
