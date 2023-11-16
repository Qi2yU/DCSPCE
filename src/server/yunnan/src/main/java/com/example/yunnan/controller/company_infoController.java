package com.example.yunnan.controller;

import com.example.yunnan.entity.company_info;
import com.example.yunnan.mapper.company_infoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class company_infoController {

    @Autowired
    private company_infoMapper company_infoMapper;

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
        id = id.substring(0,id.length()-1);
        System.out.println(id);
        company_info c_i = company_infoMapper.find_single(id);
        System.out.println("c_i:"+c_i);
        return c_i;
    }

    //按城市查询 待审核
    @RequestMapping("/company_info/selectbycity_bechecked")
    public List searchbycity(@RequestBody String id)
    {
        id = id.substring(0,id.length()-1);
        System.out.println(id);
        String values;
        switch (id) {
            case "1": values = "昆明市"; break;
            case "2": values = "曲靖市"; break;
            case "3": values = "玉溪市"; break;
            case "4": values = "丽江市"; break;
            case "5": values = "普洱市"; break;
            case "6": values = "保山市"; break;
            case "7": values = "邵通市"; break;
            case "8": values = "临沧市"; break;
            default:  values = null; break;
        }
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
        id = id.substring(0,id.length()-1);
        System.out.println(id);
        String values;
        switch (id) {
            case "1": values = "昆明市"; break;
            case "2": values = "曲靖市"; break;
            case "3": values = "玉溪市"; break;
            case "4": values = "丽江市"; break;
            case "5": values = "普洱市"; break;
            case "6": values = "保山市"; break;
            case "7": values = "邵通市"; break;
            case "8": values = "临沧市"; break;
            default:  values = null; break;
        }
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
        id = id.substring(0,id.length()-1);
        System.out.println(id);
        String values;
        switch (id) {
            case "1": values = "昆明市"; break;
            case "2": values = "曲靖市"; break;
            case "3": values = "玉溪市"; break;
            case "4": values = "丽江市"; break;
            case "5": values = "普洱市"; break;
            case "6": values = "保山市"; break;
            case "7": values = "邵通市"; break;
            case "8": values = "临沧市"; break;
            default:  values = null; break;
        }
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
