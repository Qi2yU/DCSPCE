package com.example.yunnan.service;

import com.example.yunnan.entity.CompareMountedEntity;
import com.example.yunnan.entity.TendEntity;
import com.example.yunnan.entity.TendResEntity;
import com.example.yunnan.mapper.analy_Tend_mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class analy_Tend_service {
    private List<CompareMountedEntity> mounted_city = new ArrayList<>();
    private List<CompareMountedEntity> mounted_char = new ArrayList<>() ;
    private List<CompareMountedEntity> mounted_indu = new ArrayList<>();

    public List<CompareMountedEntity> getMounted_city() {
        return mounted_city;
    }

    public List<CompareMountedEntity> getMounted_char() {
        return mounted_char;
    }

    public List<CompareMountedEntity> getMounted_indu() {
        return mounted_indu;
    }

    public void Clean_city(){
        mounted_city.clear();
    }
    public void Clean_char(){
        mounted_char.clear();
    }

    public void Clean_indu(){
        mounted_indu.clear();
    }
    private List<List<TendEntity>> data_collection = new ArrayList<>();
    private List<String> time_list = new ArrayList<>();

    public List<TendResEntity> getData_res() {
        return data_res;
    }
    private List<TendResEntity> data_res = new ArrayList<>();
    @Autowired
    private analy_Tend_mapper mapper;

    public void Clean(){
        data_res.clear();
    }
    String compute_Time(String time, String et){//字符串类型：20xx0901， 20xx0900，长度为8
        if(Integer.valueOf(time) >= Integer.valueOf(et)){
            return time;
        }
        else{
            if(time.charAt(7) == '1'){ //调查期号：1
                StringBuffer sb =  new StringBuffer(time);
                char month_last = time.charAt(5);
                char month_first = time.charAt(4);
                if(month_last == '2' && month_first =='1'){//调查期月份为12
                    sb.replace(7,8,String.valueOf('0'));
                    sb.replace(4,6,"01");
                    char year_one = time.charAt(0);
                    char year_two = time.charAt(1);
                    char year_three = time.charAt(2);
                    char year_four = time.charAt(3);
                    if(year_four == '9'){//调查期年份为xxx9
                        sb.replace(3,4,String.valueOf('0'));
                        if(year_three == '9'){//调查期年份为xx99
                            sb.replace(2,3,String.valueOf('0'));
                            if(year_two == '9'){//调查期年份为x999
                                sb.replace(1,2,String.valueOf('0'));
                                int yo_int = Integer.valueOf(String.valueOf(year_one));
                                yo_int ++;
                                sb.replace(0,1,String.valueOf(yo_int));
                                return sb.toString();
                            }
                            else {
                                int ys_int = Integer.valueOf(String.valueOf(year_two));
                                ys_int ++;
                                sb.replace(1,2,String.valueOf(ys_int));
                                return sb.toString();
                            }

                        }
                        else {
                            int yt_int = Integer.valueOf(String.valueOf(year_three));
                            yt_int ++;
                            sb.replace(2,3,String.valueOf(yt_int));
                            return sb.toString();
                        }
                    }
                    else {
                        int yl_int = Integer.valueOf(String.valueOf(year_four));
                        yl_int++;
                        sb.replace(3,4,String.valueOf(yl_int));
                        return sb.toString();

                    }

                }//调查期月份不为12
                else{
                    if(month_last == '9'){//调查期月份为9
                        sb.replace(4,6,"10");
                        sb.replace(7,8,String.valueOf('0'));
                        return sb.toString();

                    }
                    else {//调查期月份不为9或12
                        int ml_int = Integer.valueOf(String.valueOf(month_last));
                        ml_int++;
                        sb.replace(7,8,String.valueOf('0'));
                        sb.replace(5,6,String.valueOf(ml_int));
                        return sb.toString();
                    }
                }

            }
            else if(time.charAt(7) == '0'){//调查期号：0
                StringBuffer sb =  new StringBuffer(time);
                return sb.replace(7,8,String.valueOf('1')).toString();
            }
        }
        return time;
    }
    public void get_data(String start_time, String end_time, String city, String character, String industry){
        data_res = mapper.get_resname(industry,city,character);
        String time = new String(start_time);
        while(true){
            List<TendEntity>  data_signle = new ArrayList<>();
            StringBuffer time_fmt = new StringBuffer(time);//data_20xx_09_1
            time_fmt.replace(6,7,"_");//20xx09_1
            time_fmt.insert(4,"_");//20xx_09_1
            time_fmt.insert(0,"data_");//data_20xx_09_1
            data_signle =  mapper.get_data(String.valueOf(time_fmt), industry,city,character);
            if(data_signle.size() == 0){
                break;
            }
            for(TendResEntity data : data_res){
                String name = data.getCompany_name();
                Optional<TendEntity> data_found = data_signle.stream().filter(s -> s.getCompany_name().equals(name)).findFirst();
                data.add_last_num(data_found.get().getLast_people_num());
                data.add_now_num(data_found.get().getNow_people_num());
            }
            time_list.add(time);
            data_signle.get(0).setTime(time);
            data_collection.add(data_signle);
            if(Integer.valueOf(time) >= Integer.valueOf(end_time)){
                break;
            }
            time = compute_Time(time, end_time);
        }
        data_res.get(0).print_list();
    }
    public void get_mounted_city(){
        List<String> list;
        list = mapper.get_mounted_city();
        for(int i = 0; i < list.size(); i++){
            CompareMountedEntity entity = new CompareMountedEntity();
            entity.setNum(String.valueOf(i));
            entity.setName(list.get(i));
            mounted_city.add(entity);
        }
    }
    public void get_mounted_char(){
        List<String> list;

        list = mapper.get_mounted_char();
        for(int i = 0; i < list.size(); i++){
            CompareMountedEntity entity = new CompareMountedEntity();
            entity.setNum(String.valueOf(i));
            entity.setName(list.get(i));
            mounted_char.add(entity);
        }

    }
    public void get_mounted_indu(){
        List<String> list;
        list = mapper.get_mounted_indu();
        for(int i = 0; i < list.size(); i++){
            CompareMountedEntity entity = new CompareMountedEntity();
            entity.setNum(String.valueOf(i));
            entity.setName(list.get(i));
            mounted_indu.add(entity);
        }

    }
}
