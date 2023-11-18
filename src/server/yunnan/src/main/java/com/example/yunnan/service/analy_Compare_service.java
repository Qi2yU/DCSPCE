package com.example.yunnan.service;

import com.example.yunnan.entity.CompareEntity;
import com.example.yunnan.entity.CompareMountedEntity;
import com.example.yunnan.entity.Compare_lineEntity;
import com.example.yunnan.entity.Compare_tableEntity;
import com.example.yunnan.mapper.sum_databyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.yunnan.mapper.analy_Compare_mapper;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

@Service
public class analy_Compare_service {
    private sum_databyTime sum_databyTime_mapper ;
    private List<CompareEntity> start_data = new ArrayList<>();
    private List<CompareEntity> end_data = new ArrayList<>();

    public List<Compare_tableEntity> getTable_data_list() {
        return table_data_list;
    }

    private List<Compare_tableEntity> table_data_list = new ArrayList<>();



    private List<Integer>  line_data_list = new ArrayList<>();
    public List<Integer> getLine_data_list() {
        return line_data_list;
    }
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
    public void Clean_table(){

        table_data_list.clear();
    }
    public void Clean_list(){
        line_data_list.clear();
    }






    @Autowired
    private  analy_Compare_mapper  mapper;
    public void get_data(String city, String character, String industry, String start_time, String end_time){
        start_data = mapper.get_data(start_time, industry,city,character);

        end_data = mapper.get_data(end_time, industry,city,character);
        int now_num, last_num, change_num;
        String name;
        for(int i = 0; i < start_data.size(); i++){
            Compare_tableEntity table_data = new Compare_tableEntity();
            table_data.setName(start_data.get(i).getName());
            now_num = start_data.get(i).getNow_num();
            last_num = start_data.get(i).getLast_num();
            change_num = now_num - last_num;
            if(change_num < 0){
                table_data.setA_less(String.valueOf(change_num));
            }
            else {
                table_data.setA_less(String.valueOf(0));
            }
            table_data.setA_change_num(String.valueOf(abs(change_num)));
            table_data.setA_change_precent(String.valueOf(abs(change_num) * 100 / now_num)  +"%");
            table_data_list.add(table_data);
        }
        for(int i = 0; i < end_data.size();i++){
            name = end_data.get(i).getName();
            now_num = end_data.get(i).getNow_num();
            last_num = end_data.get(i).getLast_num();
            change_num = now_num - last_num;
            for(Compare_tableEntity table_data : table_data_list){
                if(table_data.getName().equals(name)){

                    if(change_num < 0){
                        table_data.setB_less(String.valueOf(change_num));
                    }
                    else {
                        table_data.setB_less(String.valueOf(0));
                    }
                    table_data.setB_change_num(String.valueOf(abs(change_num)));
                    table_data.setB_change_precent(String.valueOf(abs(change_num) * 100 / now_num) +"%");
                    break;
                }
            }
        }

    }
    public void get_line(){

        int A_last = 0, A_now = 0, B_last = 0, B_now = 0;
        for(CompareEntity data : start_data){
            A_last += data.getLast_num();
            A_now += data.getNow_num();
        }
        for(CompareEntity data : end_data){
            B_last += data.getLast_num();
            B_now += data.getNow_num();
        }
        List<Integer> num_list = new ArrayList<>();
        num_list.add(A_last);
        num_list.add(A_now);
        num_list.add(B_last);
        num_list.add(B_now);
        for(int i = 0; i < 4; i++){
            line_data_list.add(num_list.get(i));
        }
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
