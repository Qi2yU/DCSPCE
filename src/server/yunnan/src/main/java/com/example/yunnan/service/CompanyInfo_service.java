package com.example.yunnan.service;

import com.example.yunnan.mapper.Companyinfo_mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyInfo_service {

    @Autowired
    private Companyinfo_mapper companyinfoMapper;

    public void cpinfo_submit(
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
    ){
        companyinfoMapper.cpinfo_submit(
                user_id,
                company_name,
                company_id,
                city,
                district,
                phone,
                company_character,
                company_industry,
                company_business,
                linksman,
                linaddress,
                post_num,
                fax_num,
                email,
                is_valid
        );
    }
}
