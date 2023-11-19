package com.example.yunnan.controller;



import com.example.yunnan.entity.UserInfo;
import com.example.yunnan.msg.UserID;
import com.example.yunnan.service.Recorded_info_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
public class com_recorded {

    @Autowired
    private Recorded_info_service recordedInfoService;

    @RequestMapping(value="/get_recorded_info",method= RequestMethod.GET)
    public SimpleRecordedInfo get_recorded_info(UserID cid){
        String uid = cid.getUserid();
        System.out.println("uid: "+cid.getUserid());
        SimpleRecordedInfo sri = new SimpleRecordedInfo();


        List<UserInfo> list = recordedInfoService.getRecorded_info(uid);
        if(list.toArray().length == 1){
            UserInfo ui = (UserInfo)list.toArray()[0];
            sri.setIs_recorded(ui.getIs_record());
            sri.setCompanyName(ui.getUser_name());
        } else {
            sri.setIs_recorded(-1);
        }
        System.out.println(sri.toString());

        return  sri;
    }
}

class SimpleRecordedInfo{
    private int is_recorded;
    private String CompanyName;

    public int getIs_recorded() {
        return is_recorded;
    }

    public void setIs_recorded(int is_recorded) {
        this.is_recorded = is_recorded;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    @Override
    public String toString() {
        return "SimpleRecorded_info{" +
                "is_recorded=" + is_recorded +
                ", CompanyName='" + CompanyName + '\'' +
                '}';
    }
}
