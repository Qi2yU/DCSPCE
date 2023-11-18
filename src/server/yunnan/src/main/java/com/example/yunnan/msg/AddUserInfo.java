package com.example.yunnan.msg;

public class AddUserInfo {
    private String userId;
    private String userName;
    private String password;

    public AddUserInfo(String userName, String userId, String password) {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

}
