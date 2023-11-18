package com.example.yunnan.entity;

public class countryData {
    private String userName;
    private String userId;
    private int oldNum;
    private int nowNum;
    private String decReason;
    private String mainReason;
    private String secReason;
    private String explain;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Override
    public String toString() {
        return "countryData{" +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", oldNum=" + oldNum +
                ", nowNum=" + nowNum +
                ", decReason='" + decReason + '\'' +
                ", mainReason='" + mainReason + '\'' +
                ", secReason='" + secReason + '\'' +
                ", explain='" + explain + '\'' +
                '}';
    }
}
