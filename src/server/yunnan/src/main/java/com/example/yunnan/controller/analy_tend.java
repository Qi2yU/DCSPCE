package com.example.yunnan.controller;

import com.example.yunnan.entity.CompareMountedEntity;
import com.example.yunnan.entity.TendResEntity;
import com.example.yunnan.service.analy_Tend_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class analy_tend {
    @Autowired
    private analy_Tend_service service;

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

    @GetMapping("/government-pro/analy_tend/get_data")
    public List<TendResEntity> get_data(String start_time, String end_time, String city, String character, String industry){
        int day = 0;
        int period = 2;
        int step = 30 / period;
        StringBuffer sbs = new StringBuffer(start_time);//20xx_09_12 20xx_12_01
        StringBuffer sbe = new StringBuffer(end_time);

        String key = String.valueOf(step);
        int s_flag = 1, e_flag = 1;

        if(start_time.substring(8).equals(key)){
            sbs.replace(8, 10, "00");
            s_flag = 0;
        }
        if(end_time.substring(8).equals(key)){
            sbe.replace(8, 10, "00");
            e_flag = 0;
        }


        for (int i = 0; i < period; i++) {
            day += step;
            String last = "0";
            last += String.valueOf(i);
            if (Integer.valueOf(start_time.substring(8)) < day &&
                    (Integer.valueOf(start_time.substring(8)) > day - step) && s_flag == 1
            ) {
                sbs.replace(8, 10, last);
            }
            if (Integer.valueOf(end_time.substring(8)) < day &&
                    (Integer.valueOf(end_time.substring(8)) > day - step) && e_flag == 1) {
                sbe.replace(8, 10, last);
            }
        }

        sbs.deleteCharAt(7);
        sbs.deleteCharAt(4);
        sbe.deleteCharAt(7);
        sbe.deleteCharAt(4);
        start_time = String.valueOf(sbs);
        end_time = String.valueOf(sbe);
        service.get_data(start_time,end_time,city,character,industry);
        return service.getData_res();


    }


}
