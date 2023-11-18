package com.example.yunnan.entity;

public class data_2023_09_1 {
    private String user_id;
    private int last_people_num;
    private int now_people_num;
    private String decrease_reason;
    private String main_reason;
    private String second_reason;
    private String explain_reason;
    private int state;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getDecrease_reason() {
        return decrease_reason;
    }

    public void setDecrease_reason(String decrease_reason) {
        this.decrease_reason = decrease_reason;
    }

    public String getMain_reason() {
        return main_reason;
    }

    public void setMain_reason(String main_reason) {
        this.main_reason = main_reason;
    }

    public String getSecond_reason() {
        return second_reason;
    }

    public void setSecond_reason(String second_reason) {
        this.second_reason = second_reason;
    }

    public String getExplain_reason() {
        return explain_reason;
    }

    public void setExplain_reason(String explain_reason) {
        this.explain_reason = explain_reason;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "data_2023_09_1{" +
                "user_id='" + user_id + '\'' +
                ", last_people_num=" + last_people_num +
                ", now_people_num=" + now_people_num +
                ", decrease_reason='" + decrease_reason + '\'' +
                ", main_reason='" + main_reason + '\'' +
                ", second_reason='" + second_reason + '\'' +
                ", explain_reason='" + explain_reason + '\'' +
                ", state=" + state +
                '}';
    }
}
