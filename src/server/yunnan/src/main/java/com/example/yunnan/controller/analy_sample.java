package com.example.yunnan.controller;

import com.example.yunnan.entity.SampleResEntity;
import com.example.yunnan.service.analy_Sample_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class analy_sample {
    @Autowired
    private analy_Sample_service service;

    @GetMapping("/government-pro/sample/mounted")
    public List<SampleResEntity> get_data(){
        service.Clean();
        service.get_data();
        return service.getData_res();
    }

}
