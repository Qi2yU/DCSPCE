package com.example.yunnan.entity;

public class FormDataEntity {
    private String text;
    private String textarea;
    private String gov_id;

    // 省略构造函数和getter/setter方法

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextarea() {
        return textarea;
    }

    public void setTextarea(String textarea) {
        this.textarea = textarea;
    }

    public String getGov_id() {
        return gov_id;
    }

    public void setGov_id(String gov_id) {
        this.gov_id = gov_id;
    }
}
