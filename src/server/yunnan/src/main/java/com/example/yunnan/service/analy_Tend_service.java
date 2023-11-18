package com.example.yunnan.service;

import com.example.yunnan.entity.CompareMountedEntity;
import com.example.yunnan.entity.TendEntity;
import com.example.yunnan.entity.TendResEntity;
import com.example.yunnan.entity.TendTableEntity;
import com.example.yunnan.mapper.analy_Tend_mapper;
import com.example.yunnan.mapper.sum_databyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class analy_Tend_service {
    @Autowired
    private sum_databyTime sum_databyTime_mapper ;
    private List<CompareMountedEntity> mounted_city = new ArrayList<>();
    private List<CompareMountedEntity> mounted_char = new ArrayList<>() ;
    private List<CompareMountedEntity> mounted_indu = new ArrayList<>();

    private List<CompareMountedEntity> mounted_start = new ArrayList<>();
    private List<CompareMountedEntity> mounted_end = new ArrayList<>();

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

    public void Clean_time(){
        time_list.clear();
    }
    public void Clean_start(){
        mounted_start.clear();
    }
    public void Clean_end(){
        mounted_end.clear();
    }

    private List<List<TendEntity>> data_collection = new ArrayList<>();
    private List<String> time_list = new ArrayList<>();

    public List<TendResEntity> getData_res() {
        return data_res;
    }
    private List<TendResEntity> data_res = new ArrayList<>();

    private List<TendTableEntity> data_table = new ArrayList<>();
    @Autowired
    private analy_Tend_mapper mapper;

    public void Clean(){
        data_res.clear();
    }
    String compute_TimewithPeriod(String time, String et, char bound){//字符串类型：20xx0901， 20xx0900，长度为8

        if(Integer.valueOf(time) >= Integer.valueOf(et)){
            return time;
        }
        else{
            if(time.charAt(7) == bound){ //调查期号：bound 1
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
            else {//调查期号：0--bound-1
                StringBuffer sb =  new StringBuffer(time);
                char next = time.charAt(7);
                int n = next - '0';
                n = n + 1;
                return sb.replace(7,8,String.valueOf(n)).toString();
            }
        }

    }

    public List<String> get_time(String start_time, String end_time){
        String time = new String(start_time);
        while (true){
            System.out.print(time+"\n");
            StringBuffer time_fmt = new StringBuffer(time);//data_20xx_09_1
            time_fmt.replace(6,7,"_");//20xx09_1
            time_fmt.insert(4,"_");//20xx_09_1
            time_fmt.insert(0,"data_");//data_20xx_09_1

            StringBuffer sb = new StringBuffer(time);//20231001
            int n = sb.charAt(7) - '0';
            n = n + 1;


            sb.replace(7,8, String.valueOf(n));

            sb.insert(8,"号调查期");
            if(sb.charAt(6) == '0'){
                sb.replace(6,7,"第");
            }
            else {
                sb.insert(5,"第");
            }
            sb.insert(6,"月");
            sb.insert(4,"年");
            if(sb.charAt(5) == '0'){
                sb.deleteCharAt(5);
            }


            time_list.add(String.valueOf(sb));//20xx0301

            if(Integer.valueOf(time) >= Integer.valueOf(end_time)){
                break;
            }
            int bound = sum_databyTime_mapper.get_type(String.valueOf(time_fmt));
            bound = bound - 1 ;
            char bc =  (char) (bound + '0');

            time = compute_TimewithPeriod(time, end_time,bc);
        }

        return time_list;
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
                if(data_found.isPresent()){
                    data.add_last_num(data_found.get().getLast_people_num());
                    data.add_now_num(data_found.get().getNow_people_num());
                }else {
                    if(data.now_num_list.size() != 0){
                        data.add_last_num(data.now_num_list.get(data.now_num_list.size() - 1));
                    }
                    else {
                        data.add_now_num(0);
                    }

                    data.add_now_num(0);
                }

            }

            data_signle.get(0).setTime(time);
            data_collection.add(data_signle);
            if(Integer.valueOf(time) >= Integer.valueOf(end_time)){
                break;
            }
            int bound = sum_databyTime_mapper.get_type(String.valueOf(time_fmt));
            bound = bound - 1 ;
            char bc =  (char) (bound + '0');

            time = compute_TimewithPeriod(time, end_time,bc);
        }

    }
    public List<TendTableEntity> get_table(){
        data_table.clear();

        for(TendResEntity data : data_res){
            TendTableEntity table_data = new TendTableEntity();
            table_data.setCompany_name(data.getCompany_name());
            for(int i = 0 ; i < data.get_size(); i++){
                int change = data.now_num_list.get(i) - data.last_num_list.get(i);
                int percent;
                if(data.now_num_list.get(i) == 0){
                    percent = 0;
                }
                else {
                    percent = change * 100 / data.now_num_list.get(i);
                }

                table_data.add_table_data(String.valueOf(percent) + "%");
            }
            table_data.setTime_list(time_list);
            data_table.add(table_data);
        }

        return data_table;
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
    public List<CompareMountedEntity> get_mounted_start(){
        List<String> list;
        list = mapper.get_mounted_start();
        for(int i = 0; i < list.size(); i++){
            CompareMountedEntity entity = new CompareMountedEntity();
            entity.setNum(String.valueOf(i));//2023_03_0
            StringBuffer name = new StringBuffer(list.get(i));
            name.delete(0,5);
            name.insert(9,"号调查期");
            name.replace(4,5,"年");//2023年03_0
            name.delete(7,8);//2023年030
            name.insert(7,"月第");

            int n = Integer.valueOf(name.charAt(9) - '0');
            n = n+1;
            name.replace(9,10,String.valueOf(n));
            //2024年02月第1号调查期

            if(name.charAt(5) == '0'){
                name.delete(5,6);
            }

            entity.setName(String.valueOf(name));
            mounted_start.add(entity);
        }
       return mounted_start;
    }
    public List<CompareMountedEntity> get_mounted_end(){
        List<String> list;
        list = mapper.get_mounted_end();
        for(int i = 0; i < list.size(); i++){
            CompareMountedEntity entity = new CompareMountedEntity();
            entity.setNum(String.valueOf(i));
            StringBuffer name = new StringBuffer(list.get(i));
            name.delete(0,5);
            name.insert(9,"号调查期");
            name.replace(4,5,"年");//2023年03_0
            name.delete(7,8);//2023年030
            name.insert(7,"月第");

            int n = Integer.valueOf(name.charAt(9) - '0');
            n = n+1;
            name.replace(9,10,String.valueOf(n));

            if(name.charAt(5) == '0'){
                name.delete(5,6);
            }
            //2024年2月第1号调查期



            entity.setName(String.valueOf(name));
            mounted_end.add(entity);
        }
        return mounted_end;
    }
}