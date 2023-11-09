package com.example.yunnan.service;


import com.example.yunnan.entity.SampleResEntity;
import com.example.yunnan.mapper.analy_Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class analy_Sample_service {

    @Autowired
    private analy_Sample mapper;

    private List<SampleResEntity>  data_res = new ArrayList<>();
    private List<Map<String, String>>   data = new ArrayList<>();

    public List<SampleResEntity> getData_res() {
        return data_res;
    }
    public void Clean(){
        data_res.clear();
        data.clear();
    }
    public void get_data(){
        data = mapper.get_data();
        for(int i = 0; i < data.size(); i++){
            SampleResEntity sample_data = new SampleResEntity();

            sample_data.setName(data.get(i).get("city_name"));
            sample_data.setValue(data.get(i).get("company_num"));

            data_res.add(sample_data);

        }
    }


}
