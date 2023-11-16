package com.example.yunnan.mapper;

import com.example.yunnan.entity.company_info;
import com.example.yunnan.entity.user_accounts;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface company_infoMapper {

    //查询已通过数据
    @Select("select * from company_info where is_valid = 1")
    public List<company_info> find_passed();

    //查询被退回数据
    @Select("select * from company_info where is_valid = 3")
    public List<company_info> find_backed();

    //查询待审核数据
    @Select("select * from company_info where is_valid = 2")
    public List<company_info> find_be_checked();

    //查询单个数据
    @Select("select * from company_info where user_id = #{id}")
    public company_info find_single(String id);

    //按地区查询 待审核
    @Select("select * from company_info where (is_valid = 2 and city = #{values})")
    public List<company_info> selectbycity(String values);

    //按地区查询 已驳回
    @Select("select * from company_info where (is_valid = 3 and city = #{values})")
    public List<company_info> selectbycity2(String values);

    //按地区查询 已通过
    @Select("select * from company_info where (is_valid = 1 and city = #{values})")
    public List<company_info> selectbycity3(String values);

}
