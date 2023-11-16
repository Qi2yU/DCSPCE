package com.example.yunnan.entity;

public class CompareEntity {
    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLast_num() {
        return last_num;
    }

    public void setLast_num(int last_num) {
        this.last_num = last_num;
    }

    public int getNow_num() {
        return now_num;
    }

    public void setNow_num(int now_num) {
        this.now_num = now_num;
    }

    int last_num;
    int now_num;
    String name;


}
