package com.example.yunnan.entity;

public class Committed {
    private String companyId;
    private String companyName;
    private int state;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Committed{" +
                "companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", state=" + state +
                '}';
    }
}
