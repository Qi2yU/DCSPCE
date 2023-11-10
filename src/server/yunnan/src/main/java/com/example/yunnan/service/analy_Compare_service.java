package com.example.yunnan.service;

import com.example.yunnan.entity.CompareEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.yunnan.mapper.analy_Compare_mapper;

import java.util.List;

@Service
public class analy_Compare_service {

    private List<CompareEntity> start_data;
    private List<CompareEntity> end_data;
    private List<String> mounted_city;

    public List<String> getMounted_city() {
        return mounted_city;
    }

    public List<String> getMounted_char() {
        return mounted_char;
    }

    public List<String> getMounted_indu() {
        return mounted_indu;
    }

    private List<String> mounted_char;
    private List<String> mounted_indu;


    @Autowired
    private  analy_Compare_mapper  mapper;
    public void get_data(String city, String character, String industry, String start_time, String end_time){
        start_data = mapper.get_data(start_time, industry,city,character);
        end_data = mapper.get_data(end_time, industry,city,character);
    }

    public void get_mounted(){
        mounted_city = mapper.get_mounted_city();
        System.out.print(mounted_city);
    }
}
