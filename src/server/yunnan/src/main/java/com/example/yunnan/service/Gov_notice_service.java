package com.example.yunnan.service;

import com.example.yunnan.entity.notice_show_entity;
import com.example.yunnan.mapper.Gov_notice_mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Gov_notice_service {

    @Autowired
    Gov_notice_mapper govNoticeMapper;

    public void addNotice(String gov_which, String msg_title,
                          String msg_content, String msg_time, String msg_to_where) {
        govNoticeMapper.addNotice(gov_which, msg_title, msg_content, msg_time, msg_to_where);
    }

    public List<notice_show_entity> fetchNotice(String id) {
        return govNoticeMapper.fetchNotice(id);
    }

    public List<notice_show_entity> fetchAllNotice() {
        return govNoticeMapper.fetchAllNotice();
    }

    public void deletenotice(String a, String b, String c, String d) {
        govNoticeMapper.deletenotice(a, b, c, d);
    }

    public int findOne(String gov_which, String msg_title,
                       String msg_content, String msg_time) {
        return govNoticeMapper.findOne(gov_which, msg_title, msg_content, msg_time);
    }

    public void editNotice(String a, String b, String c, String d, String e, String f) {
        govNoticeMapper.editNotice(a, b, c, d, e, f);
    }
}
