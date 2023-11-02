package com.example.yunnan.controller;

import com.example.yunnan.entity.EmploymentDataEntry;
import com.example.yunnan.entity.NoticeDetailEntry;
import com.example.yunnan.entity.NoticeEntry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class gov_notice {


    @RequestMapping(value="",method= RequestMethod.POST)
    public String collect_data(@RequestBody EmploymentDataEntry ede){
        //

        ede.printMyData();
        // 写入DB
        // ...



        return "Hello SpringBoot!";
    }

    @RequestMapping(value="/gov_notice/get_all",method= RequestMethod.GET)
    public String get_all_notice(String gov_id) throws JsonProcessingException {

        // 现根据 gov_id 判断一下是省还是市，是哪个市，再到对应的表中去找。
        // 从DB读入
        // getNotice(cid);

        // 这里先抓一个默认的数据
        NoticeEntry n1 = new NoticeEntry("000","省", "省第一条通知",
                "2023/11/2",0, "11");
        NoticeEntry n2 = new NoticeEntry("001","省", "省第二条通知",
                "2023/11/2",0, "11");
        NoticeEntry n3 = new NoticeEntry("002","市", "市第一条通知",
                "2023/11/2",0, "11");

        ArrayList<NoticeEntry> an = new ArrayList<>();
        an.add(n1);
        an.add(n2);
        an.add(n3);

        ObjectMapper objectMapper = new ObjectMapper();


        System.out.println(gov_id);
        System.out.println("返回了默认的通知列表项数据");
        // 返回为json格式
        return objectMapper.writeValueAsString(an);
    }
}
