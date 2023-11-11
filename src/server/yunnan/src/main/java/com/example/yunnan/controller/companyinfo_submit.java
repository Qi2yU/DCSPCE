package com.example.yunnan.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.yunnan.entity.CompanyInfo;
import com.example.yunnan.service.CompanyInfo_service;
@RestController
@CrossOrigin
public class companyinfo_submit {

    @Autowired
    private CompanyInfo_service companyInfoService;

    @RequestMapping(value="/companyinfo_submit",method= RequestMethod.POST)
    public String info_submit(@RequestBody CompanyInfo info){
        //System.out.println(info);
        companyInfoService.cpinfo_submit(
                info.getUser_id(),
                info.getCompany_name(),
                info.getCompany_id(),
                info.getCity(),
                info.getDistrict(),
                info.getPhone(),
                info.getCompany_character(),
                info.getCompany_industry(),
                info.getCompany_business(),
                info.getLinksman(),
                info.getLinaddress(),
                info.getPost_num(),
                info.getFax_num(),
                info.getEmail(),
                info.getIs_valid()
        );
        System.out.println(info);
        return "chenggong";

    }


}
