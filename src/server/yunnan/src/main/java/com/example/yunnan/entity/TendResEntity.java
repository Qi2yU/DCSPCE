package com.example.yunnan.entity;

import java.util.ArrayList;
import java.util.List;

public class TendResEntity {
    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    private String company_name;
    public List<Integer> last_num_list = new ArrayList<>();
    public List<Integer> now_num_list = new ArrayList<>();

    public void add_last_num(int num){
        last_num_list.add(num);
    }
    public void add_now_num(int num){
        now_num_list.add(num);
    }

    public void print_list(){
        for (int i = 0; i < now_num_list.size(); i++){
            System.out.print(now_num_list.get(i) + " ");
        }
    }

}
