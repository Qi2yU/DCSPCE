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

    //备案审核通过
    @Update("update company_info set is_valid=1 where user_id=#{id}")
    public void checkpass(String id);

    //备案审核驳回
    @Update("update company_info set is_valid=3 where user_id=#{id}")
    public void checkback(String id);

    //备案审核驳回原因
    @Update("update company_info set backed_info = #{reason} where user_id=#{id}")
    public void checkbackreason(String id,String reason);

    //备案审核通过 清空backed_info
    @Update("update company_info set backed_info = '' where user_id = #{id}")
    public void clearbackedinfo(String id);

    //查询表名
    @Select("select index_data_table from research_schedule where r_time_start = #{time1} and r_time_end = #{time2}")
    String selecttablename(String time1,String time2);

    //查询相关数据
    @Select("select * from company_info where ( user_id in (select user_id from ${tablename}) and is_valid = 1)")
    public List<company_info> ffind(String tablename);

    @Select("select * from company_info where ( user_id in (select user_id from ${tablename}) and city = #{values} and is_valid = 1)")
    public List<company_info> findbycityanddata(String values,String tablename);
}
