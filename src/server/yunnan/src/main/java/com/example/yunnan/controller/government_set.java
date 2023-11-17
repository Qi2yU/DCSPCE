package com.example.yunnan.controller;

import com.example.yunnan.msg.AddUserInfo;
import com.example.yunnan.service.Government_set_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        String index_data_table = "data_"+id.substring(0, 4)+'_'+id.substring(4, 6)+'_'+id.substring(6, 8);
        System.out.println(index_data_table);
//        sql完成添加调查期然后创建一个表
        governmentSetService.insertNewTableSearch(id, r_time_s, r_time_e, f_time_s, f_time_e, is_finish, index_data_table);
        governmentSetService.createNewTable(index_data_table);
//        现在得初始化这个调查期的表；先读取当前有什么企业用户在table1中；
//        然后在上一个调查期中获取数据初始化能初始化的表；
//        最好应该就是先都赋初值，之后再update已有的数据。
        return "/government-pro/centre-1";
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
