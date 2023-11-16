package com.example.yunnan.controller;

import com.example.yunnan.entity.CompareMountedEntity;
import com.example.yunnan.entity.TendResEntity;
import com.example.yunnan.entity.TendTableEntity;
import com.example.yunnan.service.analy_Tend_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.ls.LSException;

import java.util.List;

@RestController
@CrossOrigin
public class analy_tend {
    @Autowired
    private analy_Tend_service service;
    @GetMapping("/government-pro/analy_tend/start_time")
    public List<CompareMountedEntity> mounted_start(){
        service.Clean_start();
        return service.get_mounted_start();
    }
    @GetMapping("/government-pro/analy_tend/end_time")
    public List<CompareMountedEntity> mounted_end(){
        service.Clean_end();
        return service.get_mounted_end();
    }
    @GetMapping("/government-pro/analy_tend/city")
    public List<CompareMountedEntity> mounted_city(){
        service.Clean_city();
        service.get_mounted_city();
        return service.getMounted_city();
    }

    @GetMapping("/government-pro/analy_tend/char")
    public List<CompareMountedEntity> mounted_char(){
        service.Clean_char();
        service.get_mounted_char();
        return service.getMounted_char();
    }

    @GetMapping("/government-pro/analy_tend/indu")
    public List<CompareMountedEntity> mounted_indu(){
        service.Clean_indu();
        service.get_mounted_indu();
        return service.getMounted_indu();
    }
    @GetMapping("/government-pro/analy_tend/get_time")
    public  List<String> get_time(String start_time, String end_time){
        StringBuffer sbs = new StringBuffer(start_time);//2023年02月1号调查期 => 20230201
        StringBuffer sbe = new StringBuffer(end_time);

        sbs.delete(9,13);//2023年02月1
        sbe.delete(9,13);


        sbs.deleteCharAt(4);
        sbe.deleteCharAt(4);

        sbs.replace(6,7,"0");
        sbe.replace(6,7,"0");


        start_time = String.valueOf(sbs);
        end_time = String.valueOf(sbe);

        System.out.print(start_time + "\n");
        service.Clean_time();
        return service.get_time(start_time,end_time);
    }

    @GetMapping("/government-pro/analy_tend/get_data")
    public List<TendResEntity> get_data(String start_time, String end_time, String city, String character, String industry){

        StringBuffer sbs = new StringBuffer(start_time);//2023年02月1号调查期 => 20230201
        StringBuffer sbe = new StringBuffer(end_time);

        sbs.delete(9,13);//2023年02月1
        sbe.delete(9,13);


        sbs.deleteCharAt(4);
        sbe.deleteCharAt(4);

        sbs.replace(6,7,"0");
        sbe.replace(6,7,"0");



        start_time = String.valueOf(sbs);
        end_time = String.valueOf(sbe);

        service.get_data(start_time,end_time,city,character,industry);
        return service.getData_res();


    }

    @GetMapping("/government-pro/analy_tend/get_table")
    public List<TendTableEntity> get_table(){
        return service.get_table();
    }
}
