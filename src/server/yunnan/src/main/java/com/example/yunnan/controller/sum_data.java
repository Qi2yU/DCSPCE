package com.example.yunnan.controller;

import com.example.yunnan.entity.SumResEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.yunnan.entity.SumEntity;
import com.example.yunnan.service.sum_databyTime_service;
import java.util.List;

@RestController


public class sum_data {
    @Autowired
    private sum_databyTime_service sum_Servic ;
    private int sevice_type;
    @GetMapping("/sum")
    @ResponseBody
    public List<SumResEntity> get_sum(String sum_id, String start_time, String end_time){
            System.out.print(sum_id);
            if(sum_id.equals("调查期") || sum_id.equals("企业年度")|| sum_id.equals("企业月度")|| sum_id.equals("企业季度")){
                switch (sum_id){
                    case"调查期":
                        sevice_type = 0;
                        break;
                    case"企业月度":
                        sevice_type = 1;
                        break;
                    case"企业季度":
                        sevice_type = 2;
                        break;
                    case"企业年度":
                        sevice_type = 3;
                        break;
                    default:
                        sevice_type = 0;
                        break;
                }
                System.out.print("按调查期汇总");
                StringBuffer sbs =  new StringBuffer(start_time);
                StringBuffer sbe =  new StringBuffer(end_time);
                sum_Servic.get_datafortime(start_time, end_time);
                sum_Servic.sum_datafortime(sevice_type);
                //sum_Servic.debug_show();
                return sum_Servic.give_res();
            }
            else if(sum_id.equals("企业性质") ){

            }

            return sum_Servic.give_res();

    }

}
