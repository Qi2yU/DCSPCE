package com.example.yunnan.entity;

public class searchtableInfo_entity {
    private String id;
    private String r_time_start;
    private String r_time_end;
    private int is_finished;
    private int type;

    public searchtableInfo_entity(String id, String r_s, String r_e, int is_f, int type) {
        this.id = id;
        this.r_time_start = r_s;
        this.r_time_end = r_e;
        this.is_finished = is_f;
        this.type = type;
    }

    public String getR_time_start() {
        return r_time_start;
    }

    public String getR_time_end() {
        return r_time_end;
    }

    public int getIs_finished() {
        return is_finished;
    }

    public String getId() {
        return id;
    }

    public int getType() {
        return type;
    }
}
