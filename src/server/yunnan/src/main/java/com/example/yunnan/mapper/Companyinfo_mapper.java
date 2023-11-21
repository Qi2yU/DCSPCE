package com.example.yunnan.mapper;

import com.example.yunnan.entity.CompanyInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Companyinfo_mapper {

//    @Insert("insert into company_info values (#{user_id}, #{company_name}, #{city}, #{district}, #{phone}, #{company_character}, #{company_industry}, #{company_business}, #{linksman}, #{linkaddress}, #{post_num}, #{fax_num}, #{email})")
    void cpinfo_submit(
            String user_id,
            String company_name,
            String company_id,
            String city,
            String district,
            String phone,
            String company_character,
            String company_industry,
            String company_business,
            String linksman,
            String linaddress,
            String post_num,
            String fax_num,
            String email,
            int is_valid
    );

    void cpinfo_update(
            String user_id,
            String company_name,
            String company_id,
            String city,
            String district,
            String phone,
            String company_character,
            String company_industry,
            String company_business,
            String linksman,
            String linaddress,
            String post_num,
            String fax_num,
            String email,
            int is_valid
    );

    List<CompanyInfo> get_com_info_com(String uid);
}
