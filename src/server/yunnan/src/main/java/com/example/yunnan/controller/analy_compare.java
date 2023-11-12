package com.example.yunnan.controller;

import com.example.yunnan.entity.CompareMountedEntity;
import com.example.yunnan.entity.Compare_tableEntity;
import com.example.yunnan.service.analy_Compare_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class analy_compare {


    @Autowired
    private analy_Compare_service service;
    @GetMapping("/government-pro/analy_compare/city")
    public List<CompareMountedEntity> mounted_city(){
        service.Clean_city();
        service.get_mounted_city();
        return service.getMounted_city();
    }

    @GetMapping("/government-pro/analy_compare/char")
    public List<CompareMountedEntity> mounted_char(){
        service.Clean_char();
        service.get_mounted_char();
        return service.getMounted_char();
    }

    @GetMapping("/government-pro/analy_compare/indu")
    public List<CompareMountedEntity> mounted_indu(){
        service.Clean_indu();
        service.get_mounted_indu();
        return service.getMounted_indu();
    }

    @GetMapping("/government-pro/analy_compare/get_data")
    public List<Compare_tableEntity> get_data(String start_time, String end_time, String city, String character, String industry) {
        service.Clean_table();


        int day = 0;
        int period = 2;
        int step = 30 / period;
        StringBuffer sbs = new StringBuffer(start_time);//20xx_09_12 20xx_12_01
        StringBuffer sbe = new StringBuffer(end_time);
        String key = String.valueOf(step);
        int s_flag = 1, e_flag = 1;

        System.out.print(start_time.substring(5,7)+"\n");

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

        StringBuffer st_fmt = new StringBuffer(start_time);//data_20xx_09_1
        st_fmt.replace(6,7,"_");//20xx09_1
        st_fmt.insert(4,"_");//20xx_09_1
        st_fmt.insert(0,"data_");//data_20xx_09_1

        StringBuffer et_fmt = new StringBuffer(end_time);//data_20xx_09_1
        et_fmt.replace(6,7,"_");//20xx09_1
        et_fmt.insert(4,"_");//20xx_09_1
        et_fmt.insert(0,"data_");//data_20xx_09_1

        service.get_data(city, character, industry,String.valueOf(st_fmt), String.valueOf(et_fmt));
        return service.getTable_data_list();


    }


}
