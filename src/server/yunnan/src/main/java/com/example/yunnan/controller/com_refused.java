package com.example.yunnan.controller;

import com.example.yunnan.entity.comInfoRefused;
import com.example.yunnan.entity.empInfoRefused;
import com.example.yunnan.entity.res_time;
import com.example.yunnan.msg.UserID;
import com.example.yunnan.service.Data_query_service;
import com.example.yunnan.service.Refused_info_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class com_refused {

    @Autowired
    private Data_query_service dataQueryService;
    @Autowired
    private Refused_info_service refused_info_service;
    @RequestMapping(value="/get_refused_info",method= RequestMethod.GET)
    public Refused_info get_refused_info(UserID cid){

        Refused_info ri = new Refused_info();
        //默认没有驳回信息
        ri.setIs_refused(0);

        System.out.println(cid.getUserid());
        // 还需补上备案审核的驳回信息
        List<comInfoRefused> cominfo = new ArrayList<>();
        cominfo = refused_info_service.getComInfoRefused(cid.getUserid());
        if(cominfo.toArray().length == 1){
            ri.setComInfoRefused(cominfo.toArray()[0].toString());
            if(!ri.getComInfoRefused().isEmpty()){
                ri.setIs_refused(1);
                return ri;
            }
        }

        // 获取当前的调查期表
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = formatter.format(date);
        System.out.println(nowDate);
        List<res_time> list= dataQueryService.getTable(nowDate, nowDate);


        if (list.toArray().length == 1){
            // 对当前表进行修改，查询是否存在驳回信息，如果存在就加入
            String tableName= list.get(0).getTableName();
            List<empInfoRefused> empinfo = new ArrayList<>();
            empinfo = refused_info_service.getEmpInfoRefused(tableName, cid.getUserid());
            if(empinfo.toArray().length == 1){

                ri.setEmplInfoRefused(empinfo.toArray()[0].toString());
                if(!ri.getEmplInfoRefused().isEmpty()) ri.setIs_refused(1);
            }
        }
        System.out.println(ri.toString());
        return ri;
    }
}

class Refused_info{
    private int is_refused;
    private String comInfoRefused;

    @Override
    public String toString() {
        return "Refused_info{" +
                "is_refused=" + is_refused +
                ", comInfoRefused='" + comInfoRefused + '\'' +
                ", emplInfoRefused='" + emplInfoRefused + '\'' +
                '}';
    }

    private String emplInfoRefused;

    public int getIs_refused() {
        return is_refused;
    }

    public void setIs_refused(int is_refused) {
        this.is_refused = is_refused;
    }

    public String getComInfoRefused() {
        return comInfoRefused;
    }

    public void setComInfoRefused(String comInfoRefused) {
        this.comInfoRefused = comInfoRefused;
    }

    public String getEmplInfoRefused() {
        return emplInfoRefused;
    }

    public void setEmplInfoRefused(String emplInfoRefused) {
        this.emplInfoRefused = emplInfoRefused;
    }
}