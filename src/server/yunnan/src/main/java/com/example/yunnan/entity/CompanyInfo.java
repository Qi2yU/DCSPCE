package com.example.yunnan.entity;

public class CompanyInfo {
    private String user_id;
    private String company_name;
    private String company_id;
    private String city;
    private String district;
    private String phone;
    private String company_character;
    private String company_industry;
    private String company_business;
    private String linksman;
    private String linaddress;
    private String post_num;
    private String fax_num;
    private String email;
    private int is_valid;

    @Override
    public String toString() {
        return "CompanyInfo{" +
                "user_id='" + user_id + '\'' +
                ", company_name='" + company_name + '\'' +
                ", company_id='" + company_id + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", phone='" + phone + '\'' +
                ", company_character='" + company_character + '\'' +
                ", company_industry='" + company_industry + '\'' +
                ", company_bussiness='" + company_business + '\'' +
                ", linksman='" + linksman + '\'' +
                ", linkaddress='" + linaddress + '\'' +
                ", post_num='" + post_num + '\'' +
                ", fax_num='" + fax_num + '\'' +
                ", email='" + email + '\'' +
                ", is_valid=" + is_valid +
                '}';
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany_character() {
        return company_character;
    }

    public void setCompany_character(String company_character) {
        this.company_character = company_character;
    }

    public String getCompany_industry() {
        return company_industry;
    }

    public void setCompany_industry(String company_industry) {
        this.company_industry = company_industry;
    }

    public String getCompany_business() {
        return company_business;
    }

    public void setCompany_bussiness(String company_bussiness) {
        this.company_business = company_bussiness;
    }

    public String getLinksman() {
        return linksman;
    }

    public void setLinksman(String linksman) {
        this.linksman = linksman;
    }

    public String getLinaddress() {
        return linaddress;
    }

    public void setLinaddress(String linaddress) {
        this.linaddress = linaddress;
    }

    public String getPost_num() {
        return post_num;
    }

    public void setPost_num(String post_num) {
        this.post_num = post_num;
    }

    public String getFax_num() {
        return fax_num;
    }

    public void setFax_num(String fax_num) {
        this.fax_num = fax_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(int is_valid) {
        this.is_valid = is_valid;
    }

}
