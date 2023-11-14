package com.example.yunnan.entity;

import java.util.List;

public class TendEntity {
    private String company_name;
    private int last_people_num;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public int getLast_people_num() {
        return last_people_num;
    }

    public void setLast_people_num(int last_people_num) {
        this.last_people_num = last_people_num;
    }

    public int getNow_people_num() {
        return now_people_num;
    }

    public void setNow_people_num(int now_people_num) {
        this.now_people_num = now_people_num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private int now_people_num;
    private String time;
}
