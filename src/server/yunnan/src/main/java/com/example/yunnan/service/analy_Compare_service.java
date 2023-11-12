package com.example.yunnan.service;

import com.example.yunnan.entity.CompareEntity;
import com.example.yunnan.entity.CompareMountedEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.yunnan.mapper.analy_Compare_mapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class analy_Compare_service {

    private List<CompareEntity> start_data = new ArrayList<>();
    private List<CompareEntity> end_data = new ArrayList<>();
    private List<CompareMountedEntity> mounted_city = new ArrayList<>();
    private List<CompareMountedEntity> mounted_char = new ArrayList<>() ;
    private List<CompareMountedEntity> mounted_indu = new ArrayList<>();

    public List<CompareMountedEntity> getMounted_city() {
        return mounted_city;
    }

    public List<CompareMountedEntity> getMounted_char() {
        return mounted_char;
    }

    public List<CompareMountedEntity> getMounted_indu() {
        return mounted_indu;
    }

    public void Clean_city(){
        mounted_city.clear();
    }
    public void Clean_char(){
        mounted_char.clear();
    }

    public void Clean_indu(){
        mounted_indu.clear();
    }






    @Autowired
    private  analy_Compare_mapper  mapper;
    public void get_data(String city, String character, String industry, String start_time, String end_time){
        start_data = mapper.get_data(start_time, industry,city,character);
        end_data = mapper.get_data(end_time, industry,city,character);
    }

    public void get_mounted_city(){
        List<String> list;
        list = mapper.get_mounted_city();
        for(int i = 0; i < list.size(); i++){
            CompareMountedEntity entity = new CompareMountedEntity();
            entity.setNum(String.valueOf(i));
            entity.setName(list.get(i));
            mounted_city.add(entity);
        }
    }
    public void get_mounted_char(){
        List<String> list;

        list = mapper.get_mounted_char();
        for(int i = 0; i < list.size(); i++){
            CompareMountedEntity entity = new CompareMountedEntity();
            entity.setNum(String.valueOf(i));
            entity.setName(list.get(i));
            mounted_char.add(entity);
        }

    }
    public void get_mounted_indu(){
        List<String> list;
        list = mapper.get_mounted_indu();
        for(int i = 0; i < list.size(); i++){
            CompareMountedEntity entity = new CompareMountedEntity();
            entity.setNum(String.valueOf(i));
            entity.setName(list.get(i));
            mounted_indu.add(entity);
        }

    }
}
