package com.example.yunnan.controller;


import com.example.yunnan.msg.UserID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.yunnan.entity.CompanyInfo;
import com.example.yunnan.service.CompanyInfo_service;


import java.util.List;

@RestController
@CrossOrigin
public class companyinfo_submit {

    @Autowired
    private CompanyInfo_service companyInfoService;

    @RequestMapping(value="/companyinfo_submit",method= RequestMethod.POST)
    public String info_submit(@RequestBody CompanyInfo info){
        //System.out.println(info);
        String uid = info.getUser_id();
        List<CompanyInfo> list = companyInfoService.get_com_info_com(uid);
        if(list.toArray().length == 0){
            // company_info表里没找到数据
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
        } else {
            // 找到数据了
            companyInfoService.cpinfo_update(
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
        }

        System.out.println(info);
        return "chenggong";

    }

    @RequestMapping(value="/get_com_info_com",method= RequestMethod.GET)
    public CompanyInfo get_com_info_com(UserID cid){
        //System.out.println(info);
        String uid = cid.getUserid();
        List<CompanyInfo> list = companyInfoService.get_com_info_com(uid);
        if(list.toArray().length == 0){
            // company_info表里没找到数据
            return new CompanyInfo();
        } else {
            // 找到数据了
            return (CompanyInfo) list.toArray()[0];
        }


    }


}
