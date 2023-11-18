package com.example.yunnan.entity;

import java.util.ArrayList;
import java.util.List;

public class TendTableEntity {


    public void setTime_list(List<String> time_list) {
        this.time_list = time_list;
    }

    public  List<String> time_list = new ArrayList<>();

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public List<String> getTable_percent_list() {
        return table_percent_list;
    }


    public void print_list(){
        for (int i = 0; i < table_percent_list.size(); i++){
            System.out.print(table_percent_list.get(i) + " ");
        }
    }
    public String company_name;
    public List<String> table_percent_list = new ArrayList<>();
    public void add_table_data(String data){
        table_percent_list.add(data);
    }

}
