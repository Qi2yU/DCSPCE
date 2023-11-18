package com.example.yunnan.controller;

import com.example.yunnan.entity.*;
import com.example.yunnan.service.Detail_service;
//import com.sun.xml.internal.messaging.saaj.util.CharReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class committed_controller {
    @Autowired
    private Detail_service detailService;

    @PostMapping("/country")
    @ResponseBody
    public void commitToCountry(@RequestBody countryData data)
    {
        System.out.println(data);
    }

    @GetMapping("/table")
    @ResponseBody
    public res_time getTable()
    {
        return detailService.getTable();
    }

    @GetMapping("/detail")
    @ResponseBody
    public detaildata getDetail(String userID){
        String tableName=detailService.getTable().getTableName();
        detaildata data=new detaildata();
        data=detailService.getDetailById(tableName,userID);

        System.out.println(data);
        return data;
    }
    @GetMapping("/count")
    @ResponseBody
    public List<DataExam> getCount(){
        String tableName=detailService.getTable().getTableName();
        List<DataExam> list=detailService.getCountByState(tableName);
        System.out.println(list);
        return list;
    }

    @GetMapping("/committed")
    @ResponseBody
    public List<CommittedTrans> getCommitted(String city){
        String tableName=detailService.getTable().getTableName();
        List<Committed> originlist =detailService.getCommittedOnCity(tableName,city);
        System.out.println(originlist);
        List<CommittedTrans> newlist = new ArrayList<>();

        for(int i=0;i<originlist.size();i++){
            CommittedTrans data=new CommittedTrans();
            data.setCompanyId(originlist.get(i).getCompanyId());
            data.setCompanyName(originlist.get(i).getCompanyName());

            if(originlist.get(i).getState()==3){
                data.setFlag1("未审核");
                data.setFlag2("未上报");
            }
            else if(originlist.get(i).getState()==4){
                data.setFlag1("已审核");
                data.setFlag2("未上报");
            }
            else if(originlist.get(i).getState()==5){
                data.setFlag1("已审核");
                data.setFlag2("已上报");
            }
            newlist.add(data);
            System.out.println(newlist);
        }
        System.out.println(newlist);
        return newlist;
    }

    @GetMapping("/retreat")
    @ResponseBody
    public List<Retreat> getRetreat(String city){
        String tableName=detailService.getTable().getTableName();
        List<Retreat> list =detailService.getRetreat(tableName,city);
        System.out.println(list);
        return list;
    }

    @GetMapping("/uncommitted")
    @ResponseBody
    public List<Uncommitted> getUncommitted(String city){
        String tableName=detailService.getTable().getTableName();
        List<Uncommitted> list=detailService.getUncommitted(tableName,city);
        System.out.println(list);
        return list;
    }

    @GetMapping("/passbypro")
    @ResponseBody
    public void passByPro(String userId){
        String tableName=detailService.getTable().getTableName();
        detailService.passByPro(tableName,userId);
    }

    @GetMapping("/singleupbypro")
    @ResponseBody
    public void singleUpByPro(String userId){
        String tableName=detailService.getTable().getTableName();
        detailService.singleUpByPro(tableName,userId);
    }

    @GetMapping("/batchupbypro")
    @ResponseBody
    public void batchUpByPro(String city){
        String tableName=detailService.getTable().getTableName();
        detailService.batchUpByPro(tableName,city);
    }

    @GetMapping("/retreatbypro")
    @ResponseBody
    public void retreatByPro(String info,String userId){
        String tableName=detailService.getTable().getTableName();
        detailService.retreatByPro(tableName,info,userId);
    }


//    市端
    @GetMapping("/getcount")
    @ResponseBody
    public List<DataExam> getCountOnCity(String city){
        String tableName=detailService.getTable().getTableName();
        List<DataExam> data =detailService.getCountOnCity(tableName,city);
        System.out.println(data);
        return data;
    }

    @GetMapping("/uncommittedoncity")
    @ResponseBody
    public List<Uncommitted> getUncommittedOnCity(String city){
        String tableName=detailService.getTable().getTableName();
        List<Uncommitted> list=detailService.getUncommittedOnCity(tableName,city);
        System.out.println(list);
        return list;
    }

    @GetMapping("/retreatoncity")
    @ResponseBody
    public List<Retreat> getRetreatOnCity(String city){
        String tableName=detailService.getTable().getTableName();
        List<Retreat> list =detailService.getRetreatOnCity(tableName,city);
        System.out.println(list);
        return list;
    }
    @GetMapping("/getcommittedoncity")
    @ResponseBody
    public List<CommittedTrans> getCommittedOnCity2(String city){
        String tableName=detailService.getTable().getTableName();
        List<Committed> originlist =detailService.getCommittedOnCity2(tableName,city);
        System.out.println(originlist);
        List<CommittedTrans> newlist = new ArrayList<>();

        for(int i=0;i<originlist.size();i++){
            CommittedTrans data=new CommittedTrans();
            data.setCompanyId(originlist.get(i).getCompanyId());
            data.setCompanyName(originlist.get(i).getCompanyName());

            if(originlist.get(i).getState()==1){
                data.setFlag1("未审核");
                data.setFlag2("未上报");
            }
            else if(originlist.get(i).getState()==2){
                data.setFlag1("已审核");
                data.setFlag2("未上报");
            }
            else {
                data.setFlag1("已审核");
                data.setFlag2("已上报");
            }

            newlist.add(data);
            System.out.println(newlist);
        }
        System.out.println(newlist);
        return newlist;
    }

    @GetMapping("/passbycity")
    @ResponseBody
    public void passByCity(String userId){
        String tableName=detailService.getTable().getTableName();
        detailService.passByCity(tableName,userId);
    }

    @GetMapping("/singleupbycity")
    @ResponseBody
    public void singleUpByCity(String userId){
        String tableName=detailService.getTable().getTableName();
        detailService.singleUpByCity(tableName,userId);
    }


    @GetMapping("/batchupbycity")
    @ResponseBody
    public void batchUpByCity(String city){
        String tableName=detailService.getTable().getTableName();
        detailService.batchUpByCity(tableName,city);
    }

    @GetMapping("/retreatbycity")
    @ResponseBody
    public void retreatByCity(String info,String userId){
        String tableName=detailService.getTable().getTableName();
        detailService.retreatByCity(tableName,info,userId);
    }
}
