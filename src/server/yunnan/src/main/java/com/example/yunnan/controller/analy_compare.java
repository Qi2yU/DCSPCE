package com.example.yunnan.controller;

import com.example.yunnan.entity.CompareMountedEntity;
import com.example.yunnan.entity.Compare_lineEntity;
import com.example.yunnan.entity.Compare_tableEntity;
import com.example.yunnan.service.analy_Compare_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class analy_compare {

    public class Return_data<T> implements Serializable {

        int id;
        List<T> data_list = new ArrayList<>();
        public Return_data(int i, List<T> data){
            id = i;
            for(T t :data){
                data_list.add(t);
            }
        }
    }


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


        StringBuffer st_fmt = new StringBuffer(start_time);//data_20xx_09_1
        st_fmt.replace(6,7,"_");//20xx09_1
        st_fmt.insert(4,"_");//20xx_09_1
        st_fmt.insert(0,"data_");//data_20xx_09_1

        StringBuffer et_fmt = new StringBuffer(end_time);//data_20xx_09_1
        et_fmt.replace(6,7,"_");//20xx09_1
        et_fmt.insert(4,"_");//20xx_09_1
        et_fmt.insert(0,"data_");//data_20xx_09_1

        service.get_data(city, character, industry,String.valueOf(st_fmt), String.valueOf(et_fmt));
        service.get_line();
        List<Return_data> res = new ArrayList<>();
        Return_data<Integer> list = new Return_data<Integer>(0,service.getLine_data_list());
        Return_data<Compare_tableEntity> table  = new Return_data<Compare_tableEntity>(1,service.getTable_data_list());
        res.add(list);
        res.add(table);
        System.out.print(res);
        return service.getTable_data_list();
    }
    @GetMapping("/government-pro/analy_compare/get_line")
    public  List<Integer> get_line(){
        service.Clean_list();
        service.get_line();
        return service.getLine_data_list();
    }
}
