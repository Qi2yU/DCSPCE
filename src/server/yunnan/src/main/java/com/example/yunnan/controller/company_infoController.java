package com.example.yunnan.controller;

import com.example.yunnan.entity.company_info;
import com.example.yunnan.mapper.company_infoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class company_infoController {

    @Autowired
    private company_infoMapper company_infoMapper;

    public String idtovalue(String id)
    {
        String values;
        switch (id) {
            case "01": values = "昆明市"; break;
            case "02": values = "曲靖市"; break;
            case "03": values = "玉溪市"; break;
            case "04": values = "丽江市"; break;
            case "05": values = "普洱市"; break;
            case "06": values = "保山市"; break;
            case "07": values = "邵通市"; break;
            case "08": values = "临沧市"; break;
            case "09": values = "楚雄彝族自治州"; break;
            case "10": values = "红河哈尼族彝族自治州"; break;
            case "11": values = "文山壮族苗族自治州"; break;
            case "12": values = "西双版纳傣族自治州"; break;
            case "13": values = "大理白族自治州"; break;
            case "14": values = "德宏傣族景颇族自治州"; break;
            case "15": values = "怒江傈僳族自治州"; break;
            case "16": values = "迪庆藏族自治州"; break;
            default:  values = null; break;
        }
        System.out.println("values:"+values);
        return values;
    }

    //查询已通过数据
    @RequestMapping("/company_info/passed")
    public List query_passed()
    {
        List<company_info> list1 = company_infoMapper.find_passed();
        System.out.println(list1);
        return list1;
    }

    //查询被退回数据
    @RequestMapping("/company_info/backed")
    public List query_backed()
    {
        List<company_info> list2 = company_infoMapper.find_backed();
        System.out.println(list2);
        return list2;
    }

    //查询待审核数据
    @RequestMapping("/company_info/be_checked")
    public List query_be_checked()
    {
        List<company_info> list3 = company_infoMapper.find_be_checked();
        System.out.println(list3);
        return list3;
    }

    //查询单个数据
    @RequestMapping("/company_info/single")
    public company_info search(@RequestBody String id)
    {
        id = id.substring(0,10);
        System.out.println(id);
        company_info c_i = company_infoMapper.find_single(id);
        System.out.println("c_i:"+c_i);
        return c_i;
    }

    //备案审核通过
    @RequestMapping("/company_info/check_passed")
    public void checkpassed(@RequestBody String id)
    {
        id = id.substring(0,10);
        System.out.println(id);
        company_infoMapper.checkpass(id);
    }

    //备案审核驳回
    @RequestMapping("/company_info/check_backed")
    public void checkbacked(@RequestBody String id)
    {
        id = id.substring(0,10);
        System.out.println(id);
        company_infoMapper.checkback(id);
    }

    //备案审核驳回原因
    @RequestMapping("/company_info/check_backed_reason")
    public void checkbackedreason(@RequestBody Map<String,Object> param)
    {
        System.out.println(param.get("param1"));
        String id = (String) param.get("param1");
        System.out.println(param.get("param2"));
        String reason = (String) param.get("param2");
        company_infoMapper.checkbackreason(id,reason);
    }

    //按城市查询 待审核
    @RequestMapping("/company_info/selectbycity_bechecked")
    public List searchbycity(@RequestBody String id)
    {
        id = id.substring(0,2);
        System.out.println(id);
        String values = idtovalue(id);
        List<company_info> l;
        if(values==null)
        {
            l = company_infoMapper.find_be_checked();
            System.out.println(l);
        }
        else
        {
            l = company_infoMapper.selectbycity(values);
            System.out.println(l);
        }
        return l;
    }

    //按城市查询 已驳回
    @RequestMapping("/company_info/selectbycity_backed")
    public List searchbycity2(@RequestBody String id)
    {
        id = id.substring(0,2);
        System.out.println(id);
        String values = idtovalue(id);
        List<company_info> l;
        if(values==null)
        {
            l = company_infoMapper.find_backed();
            System.out.println(l);
        }
        else
        {
            l = company_infoMapper.selectbycity2(values);
            System.out.println(l);
        }
        return l;
    }

    //按城市查询 已通过
    @RequestMapping("/company_info/selectbycity_passed")
    public List searchbycity3(@RequestBody String id)
    {
        id = id.substring(0,2);
        System.out.println(id);
        String values = idtovalue(id);
        List<company_info> l;
        if(values==null)
        {
            l = company_infoMapper.find_passed();
            System.out.println(l);
        }
        else
        {
            l = company_infoMapper.selectbycity3(values);
            System.out.println(l);
        }
        return l;
    }
}
