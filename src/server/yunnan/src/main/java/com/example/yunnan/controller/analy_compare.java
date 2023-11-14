package com.example.yunnan.controller;

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
    public List<String> mounted_city(){
        service.get_mounted();
        return service.getMounted_char();
    }

    @GetMapping("/government-pro/analy_compare/char")
    public List<String> mounted_char(){
        service.get_mounted();
        return service.getMounted_char();
    }

    @GetMapping("/government-pro/analy_compare/indu")
    public List<String> mounted_indu(){
        service.get_mounted();
        return service.getMounted_indu();
    }

    @GetMapping("/government-pro/analy_compare/analy")
    public void get_data(String city, String character, String industry, String start_time, String end_time) {
        int day = 0;
        int period = 2;
        int step = 30 / period;
        StringBuffer sbs = new StringBuffer(start_time);//20xx_09_12 20xx_12_01
        StringBuffer sbe = new StringBuffer(end_time);
        for (int i = 0; i < period; i++) {
            day += step;
            String last = "0";
            last += String.valueOf(i);
            if (Integer.valueOf(start_time.substring(8)) < day &&
                    (Integer.valueOf(start_time.substring(8)) > day - step)
            ) {
                sbs.replace(8, 10, last);
            }
            if (Integer.valueOf(end_time.substring(8)) < day &&
                    (Integer.valueOf(end_time.substring(8)) > day - step)) {
                sbe.replace(8, 10, last);
            }
        }
        sbs.deleteCharAt(7);
        sbs.deleteCharAt(4);
        sbe.deleteCharAt(7);
        sbe.deleteCharAt(4);
        start_time = String.valueOf(sbs);
        end_time = String.valueOf(sbe);


    }


}
