package com.example.yunnan.entity;

public class Retreat {
    private String companyId;
    private int lastNum;
    private int nowNum;
    private String decReason;
    private String mainReason;
    private String secReason;
    private String explain;
    private String phone;
    private String companyName;
    private String info;
    private static final String state="退回修改";

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getState(){
        return state;
    }
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public int getLastNum() {
        return lastNum;
    }

    public void setLastNum(int lastNum) {
        this.lastNum = lastNum;
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
        return secReason;
    }

    public void setSecReason(String secReason) {
        this.secReason = secReason;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Retreat{" +
                "companyId='" + companyId + '\'' +
                ", lastNum=" + lastNum +
                ", nowNum=" + nowNum +
                ", decReason='" + decReason + '\'' +
                ", mainReason='" + mainReason + '\'' +
                ", secReason='" + secReason + '\'' +
                ", explain='" + explain + '\'' +
                ", phone='" + phone + '\'' +
                ", companyName='" + companyName + '\'' +
                ", state=" + state + '\'' +
                '}';
    }
}
