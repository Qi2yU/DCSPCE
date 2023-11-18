package com.example.yunnan.controller;


import com.example.yunnan.entity.EmploymentDataEntry;
import com.example.yunnan.entity.QueryDetail;
import com.example.yunnan.entity.res_time;
import com.example.yunnan.msg.UserID;
import com.example.yunnan.service.Data_collection_service;
import com.example.yunnan.service.Data_query_service;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class data_collection {

    @Autowired
    private Data_collection_service dataCollectionService;

    @Autowired
    private Data_query_service dataQueryService;

    @RequestMapping(value="/data_collection",method= RequestMethod.POST)
    public String collect_data(@RequestBody  EmploymentDataEntry ede){
        // 当期上报的就业数据写入数据库

        ede.printMyData();
        // 写入DB
        // ...
//        dataCollectionService.test_companydata(ede.getCompanyID(),
//                ede.getDocEmploymentNumber(),
//                ede.getCurEmploymentNumber(),
//                ede.getNumDecreasedReason(),
//                Integer.toString(ede.getMainReason()),
//                Integer.toString(ede.getSecondReason()),
//                ede.getReasonDetail(),
//                ede.getStatus());

        return "Hello SpringBoot!";
    }

    @RequestMapping(value="/get_company_collection_data",method= RequestMethod.GET)
    public EmploymentDataEntry get_collection_data(UserID cid) throws JsonProcessingException, JSONException {
        // 企业获取当期上报的就业数据，get需要提供企业cid
        System.out.println(cid.getUserid());

        // 获取当前时间
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = formatter.format(date);
//        String nowDate = "2023-10-10";
        System.out.println(nowDate);

        List<res_time> list= dataQueryService.getTable(nowDate, nowDate);

        EmploymentDataEntry ede = new EmploymentDataEntry();
        if(list.toArray().length == 0){
            ede.setValid(-1);
//            msg.setMsg("no collection now!");
            return ede;
        }else if(list.toArray().length == 1){
            //  从数据库取数据
            ede.setValid(0);

            String tableName= list.get(0).getTableName();

            List<QueryDetail> currentColRec = new ArrayList<>();
            currentColRec = dataQueryService.getRes1(tableName,"all",cid.getUserid(),"all",
                    "all","all","all");

            if(currentColRec.toArray().length == 0){
                // 此时应该是用户第一次进入报备系统，并且调查期创建时，并没有完成企业基本信息审核

            } else {
                QueryDetail res = (QueryDetail) currentColRec.toArray()[0];
                ede.setCompanyID(res.getUserId());
                ede.setDocEmploymentNumber(res.getOldNum());
                ede.setCurEmploymentNumber(res.getNowNum());
                ede.setNumDecreasedReason(res.getDecReason());
                ede.setMainReason(res.getMainReason());
                ede.setSecondReason(res.getSecReason());
                ede.setReasonDetail(res.getExplain());
                ede.setStatus(res.getState());
            }
            return ede;
        }else {
            // 进入这个循环说明一个日期对应两个调查期，直接寄
            ede.setValid(-2);
//            msg.setMsg("server error");
            return ede;
        }
    }
}
