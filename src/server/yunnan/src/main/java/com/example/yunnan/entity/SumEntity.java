package com.example.yunnan.entity;

public class SumEntity {

    private String kind_name ;
    private String user_id;
    private int num;


    public void setKind_name(String kind_name) {
        this.kind_name = kind_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setNum(int num) {
        this.num = num;
    }


    public String getKind_name() {
        return kind_name;
    }

    public int getNum() {
        return num;
    }

}
