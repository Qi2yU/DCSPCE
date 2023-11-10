package com.example.yunnan.entity;

public class DataExam {
    private String province;
    private int committed;
    private int uncommitted;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getCommitted() {
        return committed;
    }

    public void setCommitted(int committed) {
        this.committed = committed;
    }

    public int getUncommitted() {
        return uncommitted;
    }

    public void setUncommitted(int uncommitted) {
        this.uncommitted = uncommitted;
    }

    @Override
    public String toString() {
        return "DataExam{" +
                "province='" + province + '\'' +
                ", committed=" + committed +
                ", uncommitted=" + uncommitted +
                '}';
    }
}
