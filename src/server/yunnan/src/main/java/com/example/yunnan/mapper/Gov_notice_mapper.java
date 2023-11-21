package com.example.yunnan.mapper;

import com.example.yunnan.entity.notice_show_entity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Gov_notice_mapper {
    void addNotice(String gov_which, String msg_title,
                          String msg_content, String msg_time, String msg_to_where);

    List<notice_show_entity> fetchNotice(String id);

    List<notice_show_entity> fetchAllNotice();

    void deletenotice(String gov, String title, String content, String time);

    int findOne(String gov, String title, String content, String time);

    void editNotice(String gov, String title, String content, String time, String where, String msg_num);
}
