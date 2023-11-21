package com.example.yunnan.entity;

public class is_end_entity {
    private String id;
    private String f_time_end;

    public is_end_entity(String id, String f_time_end) {
        this.id=id;
        this.f_time_end=f_time_end;
    }

    public String getId() {
        return id;
    }

    public String getF_time_end() {
        return f_time_end;
    }
}
