package com.example.yunnan.entity;

import java.lang.ref.PhantomReference;

public class research_schedule {
    private String id;
    private String r_time_start;
    private String r_time_end;
    private String f_time_start;
    private String f_time_end;
    private int is_finish;
    private String index_data_table;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getR_time_start() {
        return r_time_start;
    }

    public void setR_time_start(String r_time_start) {
        this.r_time_start = r_time_start;
    }

    public String getR_time_end() {
        return r_time_end;
    }

    public void setR_time_end(String r_time_end) {
        this.r_time_end = r_time_end;
    }

    public String getF_time_start() {
        return f_time_start;
    }

    public void setF_time_start(String f_time_start) {
        this.f_time_start = f_time_start;
    }

    public String getF_time_end() {
        return f_time_end;
    }

    public void setF_time_end(String f_time_end) {
        this.f_time_end = f_time_end;
    }

    public int getIs_finish() {
        return is_finish;
    }

    public void setIs_finish(int is_finish) {
        this.is_finish = is_finish;
    }

    public String getIndex_data_table() {
        return index_data_table;
    }

    public void setIndex_data_table(String index_data_table) {
        this.index_data_table = index_data_table;
    }

    @Override
    public String toString() {
        return "research_schedule{" +
                "id='" + id + '\'' +
                ", r_time_start='" + r_time_start + '\'' +
                ", r_time_end='" + r_time_end + '\'' +
                ", f_time_start='" + f_time_start + '\'' +
                ", f_time_end='" + f_time_end + '\'' +
                ", is_finish=" + is_finish +
                ", index_data_table='" + index_data_table + '\'' +
                '}';
    }
}
