package com.example.yunnan.entity;

public class CommittedTrans {
    private String companyId;
    private String companyName;
    private String flag1;
    private String flag2;

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

    public String getFlag1() {
        return flag1;
    }

    public void setFlag1(String flag1) {
        this.flag1 = flag1;
    }

    public String getFlag2() {
        return flag2;
    }

    public void setFlag2(String flag2) {
        this.flag2 = flag2;
    }

    @Override
    public String toString() {
        return "CommittedTrans{" +
                "companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", flag1='" + flag1 + '\'' +
                ", flag2='" + flag2 + '\'' +
                '}';
    }
}
