package com.example.yunnan.entity;

public class QueryDetail {
    private String userId;
    private String character;
    private String industry;
    private String city;
    private String district;
    private int oldNum;
    private int nowNum;
    private String decReason;
    private String mainReason;
    private String SecReason;
    private String explain;
    private int state;
    private String companyName;

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getOldNum() {
        return oldNum;
    }

    public void setOldNum(int oldNum) {
        this.oldNum = oldNum;
    }

    public int getNowNum() {
        return nowNum;
    }

    public void setNowNum(int nowNum) {
        this.nowNum = nowNum;
    }

    public String getDecReason() {
        return decReason;
    }

    public void setDecReason(String decReason) {
        this.decReason = decReason;
    }

    public String getMainReason() {
        return mainReason;
    }

    public void setMainReason(String mainReason) {
        this.mainReason = mainReason;
    }

    public String getSecReason() {
        return SecReason;
    }

    public void setSecReason(String secReason) {
        SecReason = secReason;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName=companyName;
    }
}
