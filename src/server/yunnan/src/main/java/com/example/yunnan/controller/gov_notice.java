package com.example.yunnan.controller;

import com.example.yunnan.entity.notice_show_entity;
import com.example.yunnan.msg.CityMap;
import com.example.yunnan.service.Gov_notice_service;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
public class gov_notice {
    @Autowired
    Gov_notice_service govNoticeService;

    @PostMapping("/gov_notice/addnotice")
    public String addNotice(@RequestBody NoticeAddInfo noticeAddInfo) {
        System.out.println(noticeAddInfo.msg_title);
        System.out.println(noticeAddInfo.msg_content);

        LocalDate currenttime = LocalDate.now();
        int year = currenttime.getYear();
        int month = currenttime.getMonthValue();
        int day = currenttime.getDayOfMonth();
        String msg_time = String.format("%4d-%2d-%2d", year, month,day);

        String msg_num = noticeAddInfo.userId.substring(0,4)+String.format("%4d%2d%2d",year,month,day);
        String gov_which = CityMap.mapTocity(noticeAddInfo.userId.substring(0,4));
        System.out.println("msg_num:"+msg_num+"\nmsg_time:"+msg_time);

        String msg_to_where = (noticeAddInfo.where==null) ? "5300" : noticeAddInfo.where;
        System.out.println("地区:"+msg_to_where);
        govNoticeService.addNotice(gov_which, noticeAddInfo.msg_title,
                noticeAddInfo.msg_content, msg_time, msg_to_where);
        return "success";
    }

    @PostMapping ("/gov_notice/get_all")
    public List<notice_show_entity> get_all_notice(@RequestBody UserId userId){
        String id = userId.userId.substring(0, 4);
        System.out.println(userId+"---"+id);
        List<notice_show_entity> resultlist;
        if(Objects.equals(id, "5300")) {
            resultlist = govNoticeService.fetchAllNotice();
            System.out.println("省用户查询");
        } else {
            resultlist = govNoticeService.fetchNotice(id);
        }
        System.out.println(resultlist);
        return resultlist;
    }

    @PostMapping("/gov_notice/deleteNotice")
    public void deleteNotice(@RequestBody deleteInfo deleteinfo) {
        System.out.println(deleteinfo.msg_time);
        govNoticeService.deletenotice(deleteinfo.gov_which, deleteinfo.msg_title,
                deleteinfo.msg_content, deleteinfo.msg_time);
    }

    @PostMapping("/gov_notice/findOne")
    public int findOne(@RequestBody deleteInfo deleteinfo) {
        int id = govNoticeService.findOne(deleteinfo.gov_which, deleteinfo.msg_title,
                deleteinfo.msg_content, deleteinfo.msg_time);
        System.out.println(id);
        return id;
    }

    @PostMapping("/gov_notice/editNotice")
    public String editNotice(@RequestBody NoticeEditInfo noticeEditInfo) {
        String userId = noticeEditInfo.userId;
        System.out.println("当前操作用户："+userId);
        System.out.println("修改后发布地："+noticeEditInfo.msg_to_where);
        String gov_which = CityMap.mapTocity(userId.substring(0,4));

        LocalDate currenttime = LocalDate.now();
        int year = currenttime.getYear();
        int month = currenttime.getMonthValue();
        int day = currenttime.getDayOfMonth();
        String msg_time = String.format("%4d-%2d-%2d", year, month,day);

        govNoticeService.editNotice(gov_which, noticeEditInfo.msg_title,
                noticeEditInfo.msg_content, msg_time,
                noticeEditInfo.msg_to_where, noticeEditInfo.msg_num);
        return "success";
    }

    public static class deleteInfo {
        String gov_which;
        String msg_title;
        String msg_content;
        String msg_time;
        public deleteInfo(String gov_which, String msg_title, String msg_content, String msg_time) {
            this.gov_which=gov_which;
            this.msg_title=msg_title;
            this.msg_content=msg_content;
            this.msg_time=msg_time;
        }
    }

    public static class UserId {
        private String userId;
        @JsonCreator
        public UserId(@JsonProperty("userId") String id) {
            this.userId = id;
        }
    }

    public static class NoticeEditInfo {
        String userId;
        String msg_title;
        String msg_content;

        String msg_to_where;

        String msg_num;

        public NoticeEditInfo(String userId, String ti, String cont, String where, String id) {
            this.userId=userId;
            this.msg_title=ti;
            this.msg_content=cont;
            this.msg_to_where=where;
            this.msg_num=id;
        }

        public String getMsg_num() {
            return msg_num;
        }

        public String getWhere() {
            return msg_to_where;
        }

        public String getMsg_content() {
            return msg_content;
        }

        public String getMsg_title() {
            return msg_title;
        }
    }

    public static class NoticeAddInfo {
        String userId;
        String msg_title;
        String msg_content;

        String where;

        public NoticeAddInfo(String id,String ti, String cont, String where) {
            this.userId=id;
            this.msg_title=ti;
            this.msg_content=cont;
            this.where=where;
        }

        public String getUserId() {
            return userId;
        }

        public String getWhere() {
            return where;
        }

        public String getMsg_content() {
            return msg_content;
        }

        public String getMsg_title() {
            return msg_title;
        }
    }

}
