package com.example.yunnan.entity;

public class notice_show_entity {

    private String gov_which;
    private String msg_title;
    private String msg_content;
    private String msg_time;

    public notice_show_entity(String s1, String s2, String s3, String s4) {
        this.gov_which = s1;
        this.msg_title = s2;
        this.msg_content = s3;
        this.msg_time = s4;
    }

    public String getMsg_title() {
        return msg_title;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public String getGov_which() {
        return gov_which;
    }

    public String getMsg_time() {
        return msg_time;
    }
}
