package com.example.yunnan.entity;

public class res_time {
    private String start_time;
    private String end_time;
    private String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "res_time{" +
                "start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", tableName='" + tableName + '\'' +
                '}';
    }
}
