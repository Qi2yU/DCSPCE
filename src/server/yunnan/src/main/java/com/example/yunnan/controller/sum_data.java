package com.example.yunnan.controller;

import com.example.yunnan.entity.SumEntity;
import com.example.yunnan.entity.SumMountedEntity;
import com.example.yunnan.entity.SumResEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.yunnan.service.sum_databyTime_service;
import java.util.List;

@RestController
@CrossOrigin

public class sum_data {
    @Autowired
    private sum_databyTime_service sum_Servic ;
    private int sevice_type;


    @GetMapping("/government-pro/sum/mounted")
    public List<SumMountedEntity> get_mounted(){
        sum_Servic.get_datamounted();
        return sum_Servic.retData_mounted();

    }
    @GetMapping("/government-pro/sum")
    @ResponseBody
    public List<SumResEntity> get_sum(String sum_id, String start_time, String end_time){
            sum_Servic.Clean();
            int period = sum_Servic.getResearh_period();
            int day = 0;
            int step = 30/period;
            StringBuffer sbs =  new StringBuffer(start_time);//20xx_09_12 20xx_12_01
            StringBuffer sbe =  new StringBuffer(end_time);
            String key = String.valueOf(step);
            int s_flag = 1, e_flag = 1;

            if(start_time.substring(8).equals(key)){
                sbs.replace(8, 10, "00");
                s_flag = 0;
            }
            if(end_time.substring(8).equals(key)){
                sbe.replace(8, 10, "00");
                e_flag = 0;
            }
            for(int i = 0; i < period; i++){
                day += step;
                String last = "0";
                last += String.valueOf(i);
                if(Integer.valueOf(start_time.substring(8)) < day &&
                        (Integer.valueOf(start_time.substring(8)) > day-step && s_flag == 1)
                ){
                    sbs.replace(8,10,last);
                }
                if(Integer.valueOf(end_time.substring(8)) < day &&
                        (Integer.valueOf(end_time.substring(8)) > day-step)&& e_flag == 1){
                    sbe.replace(8,10,last);
                }
            }
            sbs.deleteCharAt(7);
            sbs.deleteCharAt(4);
            sbe.deleteCharAt(7);
            sbe.deleteCharAt(4);
            start_time = String.valueOf(sbs);
            end_time = String.valueOf(sbe);

            int flag = 0;
            if(sum_id.equals("调查期") || sum_id.equals("企业年度")|| sum_id.equals("企业月度")|| sum_id.equals("企业季度")){
                flag = 1;
                switch (sum_id){
                    case"调查期":
                        sevice_type = 0;
                        break;
                    case"企业月度":
                        sevice_type = 1;
                        break;
                    case"企业季度":
                        sevice_type = 2;
                        StringBuffer stm =  new StringBuffer(start_time);//20xx0901
                        StringBuffer etm =  new StringBuffer(end_time);
                        start_time = stm.replace(6,8,"00").toString();
                        end_time = etm.replace(6,8,"0"+String.valueOf(period-1)).toString();

                        break;
                    case"企业年度":
                        sevice_type = 3;
                        StringBuffer sty =  new StringBuffer(start_time);//20xx0901
                        StringBuffer ety =  new StringBuffer(end_time);
                        start_time = sty.replace(4,8,"0100").toString();
                        end_time = ety.replace(4,8,"0100").toString();
                        break;
                    default:
                        sevice_type = 0;
                        break;
                }

                sum_Servic.get_datafortime(start_time, end_time);
                sum_Servic.sum_datafortime(sevice_type);

            }
            else if(sum_id.equals("企业性质")|| sum_id.equals("所属行业")||sum_id.equals("企业地区")){
                sum_Servic.Clean();
                flag = 0;
                switch (sum_id){
                    case"企业地区":
                        sevice_type = 0;
                        break;
                    case"企业性质":
                        sevice_type = 1;
                        break;
                    case"所属行业":
                        sevice_type = 2;
                        break;
                    default:
                        sevice_type = 0;
                        break;
                }
                System.out.print("按企业性质汇总\n");
                sum_Servic.get_dataforpro(start_time, end_time,sevice_type);
                sum_Servic.sum_dataforpor();
            }



            return sum_Servic.give_res(sevice_type, flag);

    }


}
