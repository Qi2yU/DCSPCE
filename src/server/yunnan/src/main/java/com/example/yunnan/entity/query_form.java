package com.example.yunnan.entity;

public class query_form {
   private String start_time;
   private String end_time;

   private String companyName;
   private String userId;
   private String character;
   private String industry;
   private String city;
   private String district;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "query_form{" +
                "start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", companyName='" + companyName + '\'' +
                ", userId='" + userId + '\'' +
                ", character='" + character + '\'' +
                ", industry='" + industry + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                '}';
    }
}
