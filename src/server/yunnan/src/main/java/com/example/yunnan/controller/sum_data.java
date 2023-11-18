package com.example.yunnan.controller;

import com.example.yunnan.entity.CompareMountedEntity;
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
    @GetMapping("/government-pro/sum/start_year")
    public List<CompareMountedEntity> get_start_year(){
        sum_Servic.start_year_list.clear();
        sum_Servic.start_year.clear();
        sum_Servic.get_start_year();
        return sum_Servic.start_year;
    }
    @GetMapping("/government-pro/sum/end_year")
    public List<CompareMountedEntity> get_end_year(){
        sum_Servic.end_year_list.clear();
        sum_Servic.end_year.clear();
        sum_Servic.get_end_year();
        return sum_Servic.end_year;
    }
    @GetMapping("/government-pro/sum/start_month")
    public List<CompareMountedEntity> get_start_month(){
        sum_Servic.start_time_list.clear();
        sum_Servic.start_month.clear();
        sum_Servic.get_start_month();
        return sum_Servic.start_month;
    }
    @GetMapping("/government-pro/sum/end_month")
    public List<CompareMountedEntity> get_end_month(){
        sum_Servic.end_time_list.clear();
        sum_Servic.end_month.clear();
        sum_Servic.get_end_month();
        return sum_Servic.end_month;
    }
    @GetMapping("/government-pro/sum/mounted")
    public List<SumMountedEntity> get_mounted(){
        sum_Servic.get_datamounted();

        return sum_Servic.retData_mounted();
    }
    @GetMapping("/government-city/sum/mounted")
    public List<SumMountedEntity> get_mounted_city(String city){
        System.out.print(city);
        sum_Servic.get_datamounted_city(city);
        return sum_Servic.get_datamounted_city(city);

    }
    @GetMapping("/government-city/sum")
    public List<SumResEntity> get_sum(String sum_id, String start_time, String end_time, int flag_front, String city){
        StringBuffer sbs = new StringBuffer(start_time);//2023年02月1号调查期 => 20230201
        StringBuffer sbe = new StringBuffer(end_time);


        sum_Servic.Clean();
        if(flag_front == 0){

            if(sbs.length() == 13 ){
                sbs.insert(5,"0");
            }
            if(sbe.length() == 13){
                sbe.insert(5,"0");
            }
            int n = Integer.valueOf(sbs.charAt(9) - '0');

            n = n-1;
            sbs.replace(9,10,String.valueOf(n));

            int m = Integer.valueOf(sbe.charAt(9) - '0');
            m = m-1;
            sbe.replace(9,10,String.valueOf(m));

            sbs.delete(10,14);//2023年02月第1
            sbe.delete(10,14);


            sbs.deleteCharAt(4);//2023 2月第1
            sbe.deleteCharAt(4);

            //202302月第1
            sbs.replace(6,7,"0");//2023020第1
            sbe.replace(6,7,"0");

            sbs.delete(7,8);
            sbe.delete(7,8);




        }
        else if(flag_front == 1){//2023_07 =>20230700
            sbs.deleteCharAt(4);//202307
            sbe.deleteCharAt(4);

            sbs.insert(6, "00");
            sbe.insert(6, "00");
            System.out.print(sbs+"\n");

        }
        else if(flag_front == 3){//202310

            sbs.insert(6,"00");
            sbe.insert(6,"00");
            System.out.print(sbs+"\n");
            System.out.print(sbe+"\n");
        }


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

                    break;
                case"企业年度":
                    sevice_type = 3;
                    break;
                default:
                    sevice_type = 0;
                    break;
            }

            sum_Servic.get_datafortimeCity(start_time, end_time,city);

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
            sum_Servic.get_dataforproCity(start_time, end_time,sevice_type,city);

            sum_Servic.sum_dataforpor();
        }


        return sum_Servic.give_res(sevice_type, flag);

    }
    @GetMapping("/government-pro/sum")
    @ResponseBody
    public List<SumResEntity> get_sum(String sum_id, String start_time, String end_time, int flag_front){
            StringBuffer sbs = new StringBuffer(start_time);//2023年02月1号调查期 => 20230201
            StringBuffer sbe = new StringBuffer(end_time);


            sum_Servic.Clean();
            if(flag_front == 0){

                if(sbs.length() == 13 ){
                    sbs.insert(5,"0");
                }
                if(sbe.length() == 13){
                    sbe.insert(5,"0");
                }
                int n = Integer.valueOf(sbs.charAt(9) - '0');

                n = n-1;
                sbs.replace(9,10,String.valueOf(n));

                int m = Integer.valueOf(sbe.charAt(9) - '0');
                m = m-1;
                sbe.replace(9,10,String.valueOf(m));

                sbs.delete(10,14);//2023年02月第1
                sbe.delete(10,14);


                sbs.deleteCharAt(4);//2023 2月第1
                sbe.deleteCharAt(4);

                //202302月第1
                sbs.replace(6,7,"0");//2023020第1
                sbe.replace(6,7,"0");

                sbs.delete(7,8);
                sbe.delete(7,8);




            }
            else if(flag_front == 1){//2023_07 =>20230700
                sbs.deleteCharAt(4);//202307
                sbe.deleteCharAt(4);

                sbs.insert(6, "00");
                sbe.insert(6, "00");
                System.out.print(sbs+"\n");

            }
            else if(flag_front == 3){//202310

                sbs.insert(6,"00");
                sbe.insert(6,"00");
                System.out.print(sbs+"\n");
                System.out.print(sbe+"\n");
            }


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

                        break;
                    case"企业年度":
                        sevice_type = 3;
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
