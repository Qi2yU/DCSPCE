package com.example.yunnan.controller;

import com.example.yunnan.entity.UserInfo_set_entity;
import com.example.yunnan.entity.is_end_entity;
import com.example.yunnan.entity.searchtableInfo_entity;
import com.example.yunnan.msg.AddUserInfo;
import com.example.yunnan.service.Government_set_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
public class government_set {
//    定义service
//    这是在一个controller中针对设置的四种操作进行不同的展示。
    @Autowired
    private Government_set_service governmentSetService;

    @PostMapping("/government-pro/setUser")
    public String addUser (@RequestBody AddUserInfo adduserinfo) {
//        这个地方注意其实AddUserInfo可以写一个private在这里头的。
        System.out.println(adduserinfo.getUserName());
        System.out.println(adduserinfo.getUserId());
        System.out.println(adduserinfo.getPassword());
        String userId = adduserinfo.getUserId();
        String userName = adduserinfo.getUserName();
        String password = adduserinfo.getPassword();
        int role = Character.getNumericValue(adduserinfo.getUserId().charAt(4));
        int is_record = (role == 2)? 0 : -1;
        governmentSetService.insertNewUser(userName, userId, password, is_record, role);
//        获得相应的信息后就应当注入数据库了。
        return "/government-pro/centre-2";
    }

    @GetMapping ("/government-pro/setUserList")
    public List<UserInfo_set_entity> setUserList () {
        List<UserInfo_set_entity> userList = governmentSetService.find_all_user();
        System.out.println(userList);
        return userList;
    }

    @GetMapping("/government-pro/setSearchList")
    public List<searchtableInfo_entity> setSearchtableList() {
//        判断一下调查期是否结束？找到目前未结束的调查期，然后判断一下
        List<is_end_entity> idList = governmentSetService.schedule_notend();
        LocalDate currentTime = LocalDate.now();
        int month = currentTime.getMonthValue();
        int day = currentTime.getDayOfMonth();
        for(is_end_entity item1 : idList) {
            int table_month = Integer.parseInt(item1.getF_time_end().substring(5, 7));
            int table_day = Integer.parseInt(item1.getF_time_end().substring(8, 10));
            if(table_month<=month && table_day < day) {
                governmentSetService.change_finish(item1.getId());
            }
        }

        List<searchtableInfo_entity> tablelist = governmentSetService.findalltable();
        System.out.println(tablelist);
        return tablelist;
    }


//    添加调查期
    @PostMapping("/government-pro/setTable")
    public String addTableSearch (@RequestBody TableSearchInfo tableSearchInfo) {
        System.out.println(tableSearchInfo.r_time_start);
        System.out.println(tableSearchInfo.r_time_end);
        System.out.println(tableSearchInfo.f_time_start);
        System.out.println(tableSearchInfo.f_time_end);
//        调查期编号id
        String r_time_s = tableSearchInfo.r_time_start;
        String r_time_e = tableSearchInfo.r_time_end;
        String f_time_s = tableSearchInfo.f_time_start;
        String f_time_e = tableSearchInfo.f_time_end;
        String id = r_time_s.replaceAll("[^0-9]", "");
        int is_finish = 0;
        int type = tableSearchInfo.theType();
        String index_data_table = "data_"+id.substring(0, 4)+'_'+id.substring(4, 6)+'_'+(type==1?0:(type==2)?1:2);
        System.out.println(index_data_table);
//        sql完成添加调查期然后创建一个表
        governmentSetService.insertNewTableSearch(id, r_time_s, r_time_e, f_time_s, f_time_e, type,  is_finish, index_data_table);
//        无论如何为了保证程序的正确运行测试环境，直接先删表。
//        governmentSetService.dropTable(index_data_table);
        governmentSetService.createNewTable(index_data_table);
//        现在得初始化这个调查期的表；先读取当前有什么企业用户在table1中；
//        然后在上一个调查期中获取数据初始化能初始化的表；
//        最好应该就是先都赋初值，之后再update已有的数据。
        List<String> companyList = governmentSetService.get_companyList();
        String tablename = "data_"+id.substring(0, 4)+'_'+ Objects.toString(Integer.parseInt(id.substring(4, 6))-1)+"_0";
        for(String str_id:companyList) {
            System.out.println(str_id);
//            根据每一个user_id查询上一个调查期的数据
//            这里如何知道上一个调查期是啥时候呢，查找当前月减去1再组合得了
            Integer last_research_num = governmentSetService.get_lastnum(str_id, tablename);
            int state = 0;
            if(last_research_num != null) {
                governmentSetService.insertNum(str_id, last_research_num.intValue(), state, index_data_table);
            }
        }
        return "/government-pro/centre-1";
    }

    @GetMapping("/government-pro/getSystemInfo")
    public List<SystemInfo> getSystemInfo() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonmemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
        SystemInfo systemInfo = new SystemInfo(String.valueOf(memoryUsage.getUsed()), String.valueOf(nonmemoryUsage.getUsed()));
        List<SystemInfo> systemList = new ArrayList<>();
        systemList.add(systemInfo);
        return systemList;
    }

    private static class SystemInfo {
        String memUse;

        String nonmem;

        public SystemInfo(String use, String nonuse) {
            this.memUse=use;
            this.nonmem=nonuse;
        }

        public String getMemUse() {
            return memUse;
        }

        public String getNonmem() {
            return nonmem;
        }
    }

    private static class TableSearchInfo {
        String r_time_start;
        String r_time_end;
        String f_time_start;
        String f_time_end;

        public TableSearchInfo(String r_time_start, String r_time_end, String f_time_start, String f_time_end) {
            this.r_time_start = r_time_start;
            this.r_time_end = r_time_end;
            this.f_time_start = f_time_start;
            this.f_time_end = f_time_end;
        }

        public int theType() {
            int s_day = Integer.parseInt(this.r_time_start.substring(8, 10));
            int e_day = Integer.parseInt(this.r_time_end.substring(8, 10));
            return (e_day-s_day)>15?1:((e_day-s_day)>1?2:3);
        }

        public String getF_time_end() {
            return f_time_end;
        }

        public String getF_time_start() {
            return f_time_start;
        }

        public String getR_time_end() {
            return r_time_end;
        }

        public String getR_time_start() {
            return r_time_start;
        }
    }

}
