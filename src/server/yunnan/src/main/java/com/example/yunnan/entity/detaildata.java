package com.example.yunnan.entity;

public class detaildata {
    private String userId;
    private int oldNum;
    private int nowNum;
    private String decReason;
    private String mainReason;
    private String SecReason;
    private String explain;
    private int state;
    private String userName;
//    private UserInfo userinfo;

//    public UserInfo getUserinfo() {
//        return userinfo;
//    }

//    public void setUserinfo(UserInfo userinfo) {
//        this.userinfo = userinfo;
//    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "detaildata{" +
                "userId='" + userId + '\'' +
                ", oldNum=" + oldNum +
                ", nowNum=" + nowNum +
                ", decReason='" + decReason + '\'' +
                ", mainReason='" + mainReason + '\'' +
                ", SecReason='" + SecReason + '\'' +
                ", explain='" + explain + '\'' +
                ", state=" + state +
                ", userName='" + userName + '\'' +
                '}';
    }
}
