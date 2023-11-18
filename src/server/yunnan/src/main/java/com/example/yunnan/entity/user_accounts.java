package com.example.yunnan.entity;

public class user_accounts {
    private String user_name;
    private String user_id;
    private String password;
    private int is_record;
    private int role;



    private company_info c_i;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIs_record() {
        return is_record;
    }

    public void setIs_record(int is_record) {
        this.is_record = is_record;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public company_info getC_i() {
        return c_i;
    }

    public void setC_i(company_info c_i) {
        this.c_i = c_i;
    }

    @Override
    public String toString() {
        return "user_accounts{" +
                "user_name='" + user_name + '\'' +
                ", user_id='" + user_id + '\'' +
                ", password='" + password + '\'' +
                ", is_record=" + is_record +
                ", role=" + role +
                ", c_i=" + c_i +
                '}';
    }
}
