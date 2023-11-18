package com.example.yunnan.service;
import com.example.yunnan.entity.*;
import com.example.yunnan.mapper.sum_databyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class sum_databyTime_service {
    @Autowired
    private sum_databyTime sum_databyTime_mapper;
    String start_year_time ;
    String end_year_time ;
    public List<TimeidEntity> start_time_list = new ArrayList<>();
    public List<TimeidEntity> end_time_list = new ArrayList<>();

    public List<TimeidEntity> start_year_list = new ArrayList<>();
    public List<TimeidEntity> end_year_list = new ArrayList<>();

    public List<CompareMountedEntity> start_month = new ArrayList<>();
    public List<CompareMountedEntity> end_month = new ArrayList<>();

    public List<CompareMountedEntity> start_year = new ArrayList<>();
    public List<CompareMountedEntity> end_year = new ArrayList<>();


    private List<List<SumEntity>> data_collection = new ArrayList<>();
    private List<SumEntity> data_collectionwithpor = new ArrayList<>();
    private List<SumResEntity>  data_sum_res = new ArrayList<>();
    private List<SumResEntity>  data_sum_res_quter = new ArrayList<>();
    private List<SumResEntity>  data_sum_res_year = new ArrayList<>();

    private List<SumMountedEntity> data_mounted = new ArrayList<>();


    public List<SumMountedEntity> retData_mounted() {
        return data_mounted;
    }

    public void Clean(){
        data_collection.clear();
        data_collectionwithpor.clear();
        data_sum_res.clear();
        data_mounted.clear();
        data_sum_res_year.clear();
        data_sum_res_quter.clear();
    }

    public void get_datamounted(){
        String time = sum_databyTime_mapper.get_timeTableId();

        StringBuffer name = new StringBuffer(time);
        name.delete(0,5);//2023_10_0


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
        data_mounted = sum_databyTime_mapper.get_datamounted(time);
        for(SumMountedEntity data : data_mounted){
            data.setTime(String.valueOf(name));
        }
    }

    public List<SumMountedEntity> get_datamounted_city(String city){
        String time = sum_databyTime_mapper.get_timeTableId();
        StringBuffer name = new StringBuffer(time);
        name.delete(0,5);//2023_10_0

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
        data_mounted = sum_databyTime_mapper.get_datamounted_city(time, city);
        for(SumMountedEntity data : data_mounted){
            data.setTime(String.valueOf(name));
        }
        return data_mounted;
    }

    String compute_TimewithPeriod(String time, String et, char bound){//字符串类型：20xx0901， 20xx0900，长度为8

        if(Integer.valueOf(time) >= Integer.valueOf(et) ){
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
    public void get_datafortime(String st, String et){
        String time = new String(st);//20xx0901
        while(true){
            List<SumEntity>  data_signle = new ArrayList<>();
            StringBuffer time_fmt = new StringBuffer(time);//20xx0901
            time_fmt.replace(6,7,"_");//20xx09_1
            time_fmt.insert(4,"_");//20xx_09_1
            time_fmt.insert(0,"data_");//data_20xx_09_1

            data_signle =  sum_databyTime_mapper.get_data(String.valueOf(time_fmt));
            int bound = sum_databyTime_mapper.get_type(String.valueOf(time_fmt));
            bound = bound - 1 ;
            char bc =  (char) (bound + '0');

            if(data_signle.size() == 0){
                break;
            }
            data_signle.get(0).setKind_name(time);
            data_collection.add(data_signle);
            if(Integer.valueOf(time) >= Integer.valueOf(et)){
                break;
            }

            time = compute_TimewithPeriod(time, et, bc);
        }
    }


    public void get_datafortimeCity(String st, String et, String city){
        String time = new String(st);//20xx0901

        while(true){
            List<SumEntity>  data_signle = new ArrayList<>();
            StringBuffer time_fmt = new StringBuffer(time);//data_20xx_09_1
            time_fmt.replace(6,7,"_");//20xx09_1
            time_fmt.insert(4,"_");//20xx_09_1
            time_fmt.insert(0,"data_");//data_20xx_09_1

            data_signle =  sum_databyTime_mapper.get_dataCity(String.valueOf(time_fmt), city);
            int bound = sum_databyTime_mapper.get_type(String.valueOf(time_fmt));
            bound = bound - 1 ;
            char bc =  (char) (bound + '0');
            if(data_signle.size() == 0){
                break;
            }
            data_signle.get(0).setKind_name(time);
            data_collection.add(data_signle);
            if(Integer.valueOf(time) >= Integer.valueOf(et)){
                break;
            }
            time = compute_TimewithPeriod(time, et,bc);
        }
    }

    public void get_dataforpro(String st, String et, int type){
        String time = new String(st);//20xx0901

        while(true){
            List<SumEntity>  data_signle = new ArrayList<>();
            StringBuffer time_fmt = new StringBuffer(time);//data_20xx_09_1
            time_fmt.replace(6,7,"_");//20xx09_1
            time_fmt.insert(4,"_");//20xx_09_1
            time_fmt.insert(0,"data_");//data_20xx_09_1
            if(type == 0){
                data_signle =  sum_databyTime_mapper.get_datawithpro_city(String.valueOf(time_fmt));
                System.out.print(0);
            }
            else if(type == 1){
                data_signle =  sum_databyTime_mapper.get_datawithpro_char(String.valueOf(time_fmt));
            }
            else if(type == 2){
                data_signle =  sum_databyTime_mapper.get_datawithpro_industry(String.valueOf(time_fmt));
            }
            int bound = sum_databyTime_mapper.get_type(String.valueOf(time_fmt));
            bound = bound - 1 ;
            char bc =  (char) (bound + '0');
            data_collection.add(data_signle);
            if(Integer.valueOf(time) >= Integer.valueOf(et)){
                break;
            }
            time = compute_TimewithPeriod(time, et,bc);
        }
    }
    public void get_dataforproCity(String st, String et, int type, String city){
        String time = new String(st);//20xx0901

        while(true){
            List<SumEntity>  data_signle = new ArrayList<>();
            StringBuffer time_fmt = new StringBuffer(time);//data_20xx_09_1
            time_fmt.replace(6,7,"_");//20xx09_1
            time_fmt.insert(4,"_");//20xx_09_1
            time_fmt.insert(0,"data_");//data_20xx_09_1
            if(type == 1){
                System.out.print(city);
                data_signle =  sum_databyTime_mapper.get_datawithpro_charCity(String.valueOf(time_fmt),city);
            }
            else if(type == 2){
                data_signle =  sum_databyTime_mapper.get_datawithpro_industryCity(String.valueOf(time_fmt),city);
            }
            int bound = sum_databyTime_mapper.get_type(String.valueOf(time_fmt));
            bound = bound - 1 ;
            char bc =  (char) (bound + '0');
            data_collection.add(data_signle);
            if(Integer.valueOf(time) >= Integer.valueOf(et)){
                break;
            }
            time = compute_TimewithPeriod(time, et,bc);
        }
    }
    public void sum_dataforpor(){
        for(List<SumEntity> data_single : data_collection){
            for (SumEntity data : data_single){
                data_collectionwithpor.add(data);
            }
        }
        Map<String, Integer> collect = data_collectionwithpor.stream().
                collect(Collectors.groupingBy(SumEntity::getKind_name,Collectors.summingInt(SumEntity::getNum)));
        System.out.println(collect);
        for (String key : collect.keySet()){
            SumResEntity sumRes = new SumResEntity();
            sumRes.setKind_name(key);
            sumRes.setSum_num(collect.get(key));
            data_sum_res.add(sumRes);

        }

    }

    public void sum_dataforresearch(){

        for(List<SumEntity> data_single : data_collection){
            SumResEntity sum_res = new SumResEntity();
            sum_res.setKind_name(data_single.get(0).getKind_name());
            int sum = 0;
            for (SumEntity data : data_single){
                sum += data.getNum();
            }
            sum_res.setSum_num(sum);
            data_sum_res.add(sum_res);
        }
    }
    public void sum_dataformonth(){
        Iterator<List<SumEntity>> ite =  data_collection.iterator();
        while (ite.hasNext()){

            List<SumEntity> data_single = ite.next();

            SumResEntity sum_res = new SumResEntity();

            StringBuffer time_fmt = new StringBuffer(data_single.get(0).getKind_name());//data_20xx_09_1
            time_fmt.replace(6,7,"_");//20xx09_1
            time_fmt.insert(4,"_");//20xx_09_1
            time_fmt.insert(0,"data_");//data_20xx_09_1
            int bound = sum_databyTime_mapper.get_type(String.valueOf(time_fmt));
            bound = bound - 1 ;
            char last_char =  (char) (bound + '0');
            if(data_single.get(0).getKind_name().charAt(7) == last_char ){//20xx09xx
                sum_res.setKind_name(data_single.get(0).getKind_name().substring(0,4)+"_"+data_single.get(0).getKind_name().substring(4,6));
                int sum = 0;
                for (SumEntity data : data_single){
                    sum += data.getNum();
                }
                sum_res.setSum_num(sum);
            }
            else{
                sum_res.setKind_name(data_single.get(0).getKind_name().substring(0,4)+"_"+data_single.get(0).getKind_name().substring(4,6));
                int sum = 0;
                for (SumEntity data : data_single){
                    sum += data.getNum();
                }
                int last_num = data_single.get(0).getKind_name().charAt(7) - '0';
                for(int i = 0; i < bound - last_num   ; i++){
                    if(!ite.hasNext()){
                        break;
                    }
                    List<SumEntity> data_single_next = ite.next();
                    for (SumEntity data : data_single_next){
                        sum += data.getNum();
                    }
                }
                sum_res.setSum_num(sum);
            }
            data_sum_res.add(sum_res);
        }
    }
    public void sum_dataforquter(){
        sum_dataformonth();
        Iterator<SumResEntity> ite_quter = data_sum_res.iterator();
        while (ite_quter.hasNext()){
            SumResEntity data_month = ite_quter.next();//20xx_xx
            int month = Integer.valueOf(data_month.getKind_name().substring(5));

            int sum = 0;
            String year = data_month.getKind_name().substring(0,4);
            SumResEntity sum_quter = new SumResEntity();

            if(month>=1 && month<=3){
                sum_quter.setKind_name(year+"_01");
                sum += data_month.getSum_num();
                for(int i = 0; i < 3 - month; i++){
                    if(!ite_quter.hasNext()){
                        break;
                    }
                    SumResEntity data_month_next = ite_quter.next();
                    sum += data_month_next.getSum_num();
                }
                sum_quter.setSum_num(sum);

            }else if(month>=4 && month<=6 ){
                sum_quter.setKind_name(year+"_02");
                sum += data_month.getSum_num();
                for(int i = 0; i < 6 - month; i++){
                    if(!ite_quter.hasNext()){
                        break;
                    }

                    SumResEntity data_month_next = ite_quter.next();
                    sum += data_month_next.getSum_num();
                }
                sum_quter.setSum_num(sum);

            }else if(month>=7 && month<=9 ){
                sum_quter.setKind_name(year+"_03");
                sum += data_month.getSum_num();
                for(int i = 0; i < 9 - month; i++){
                    if(!ite_quter.hasNext()){
                        break;
                    }

                    SumResEntity data_month_next = ite_quter.next();
                    sum += data_month_next.getSum_num();
                }
                sum_quter.setSum_num(sum);

            }else if(month>=10 && month<=12 ){
                sum_quter.setKind_name(year+"_04");
                sum += data_month.getSum_num();
                for(int i = 0; i < 12 - month; i++){
                    if(!ite_quter.hasNext()){
                        break;
                    }

                    SumResEntity data_month_next = ite_quter.next();
                    sum += data_month_next.getSum_num();
                }
                sum_quter.setSum_num(sum);
            }
            data_sum_res_quter.add(sum_quter);
        }

    }
    public void sum_dataforyear(){
        sum_dataforquter();
        Iterator<SumResEntity> ite = data_sum_res_quter.iterator();
        while (ite.hasNext()){
            int sum = 0;
           SumResEntity sum_year = ite.next();
           String name = sum_year.getKind_name();
           sum_year.setKind_name(name.substring(0,4));
           System.out.print(name);
           int quter = Integer.valueOf(name.charAt(6)) - '0';
           System.out.print(quter);
           sum += sum_year.getSum_num();
           for(int i = 0; i < 4 - quter; i++){
               if(!ite.hasNext()){
                   break;
               }
               SumResEntity sum_year_next = ite.next();
               sum += sum_year_next.getSum_num();
           }
           sum_year.setSum_num(sum);
           data_sum_res_year.add(sum_year);
        }
    }
    public void sum_datafortime(int type){
        if (type == 0){
            System.out.print("按调查期汇总\n");
            sum_dataforresearch();
        }
        else if(type == 1){
            System.out.print("按企业月度汇总\n");
            sum_dataformonth();
        }
        else if(type == 2){
            System.out.print("按企业季度汇总\n");
            sum_dataforquter();
        }
        else if(type == 3){
            System.out.print("按企业年度汇总\n");
            sum_dataforyear();
        }

    }

    public void get_start_month(){
      start_time_list =   sum_databyTime_mapper.get_mounted_start();//data_2023_07_0
      Iterator<TimeidEntity> ite = start_time_list.iterator();
      while (ite.hasNext()){
          CompareMountedEntity entity = new CompareMountedEntity();
          TimeidEntity time = ite.next();
          int step = time.getType();
          String name = time.getName();
          StringBuffer sb = new StringBuffer(name);
          sb.delete(0,5);
          sb.deleteCharAt(sb.length()-1);
          sb.deleteCharAt(sb.length()-1);//2023_07
          entity.setName(String.valueOf(sb));
          entity.setNum(String.valueOf(step));
          for(int j = 0; j < step - 1; j++){
              if(!ite.hasNext()){
                  break;
              }
              ite.next();
          }
          start_month.add(entity);
      }

    }
    public void get_end_month(){
        end_time_list =   sum_databyTime_mapper.get_mounted_start();//data_2023_07_0
        Iterator<TimeidEntity> ite = end_time_list.iterator();
        while (ite.hasNext()){
            CompareMountedEntity entity = new CompareMountedEntity();
            TimeidEntity time = ite.next();
            int step = time.getType();
            String name = time.getName();
            StringBuffer sb = new StringBuffer(name);
            sb.delete(0,5);
            sb.deleteCharAt(sb.length()-1);
            sb.deleteCharAt(sb.length()-1);//2023_07
            entity.setName(String.valueOf(sb));
            entity.setNum(String.valueOf(step));
            for(int j = 0; j < step - 1; j++){
                if(!ite.hasNext()){
                    break;
                }
                ite.next();
            }
            end_month.add(entity);
        }
    }

    public void get_start_year(){
        start_year_list =   sum_databyTime_mapper.get_mounted_start();//data_2023_07_0
        Iterator<TimeidEntity> ite = start_year_list.iterator();
        while (ite.hasNext()){

            CompareMountedEntity entity = new CompareMountedEntity();
            TimeidEntity time = ite.next();
            int step = time.getType();
            String name = time.getName();
            StringBuffer sb = new StringBuffer(name);
            sb.delete(0,5);//2023_07_0
            int month;
            if(sb.charAt(5) == '0'){
                 month = sb.charAt(6) - '0';
            }else {
                 month = sb.charAt(6) - '0' + 10;
            }
            sb.delete(4,9);
            System.out.print(month+"\n");
            entity.setName(String.valueOf(sb));
            entity.setNum(String.valueOf(step));

            for(int j = 0; j < step - 1; j++){
                if(!ite.hasNext()){
                    break;
                }
                ite.next();
            }

            for(int i = 0; i < 12 - month +1; i++){
                if(!ite.hasNext()){
                    break;
                }
                ite.next();
            }
            start_year.add(entity);
        }
    }
    public void get_end_year(){
        end_year_list =   sum_databyTime_mapper.get_mounted_start();//data_2023_07_0
        Iterator<TimeidEntity> ite = end_year_list.iterator();
        while (ite.hasNext()){
            CompareMountedEntity entity = new CompareMountedEntity();
            TimeidEntity time = ite.next();
            int step = time.getType();
            String name = time.getName();
            StringBuffer sb = new StringBuffer(name);
            sb.delete(0,5);//2023_07_0
            int month;
            if(sb.charAt(5) == '0'){
                month = sb.charAt(6) - '0';
            }else {
                month = sb.charAt(6) - '0' + 10;
            }
            sb.delete(4,9);

            entity.setName(String.valueOf(sb));
            entity.setNum(String.valueOf(step));
            for(int j = 0; j < step - 1; j++){
                if(!ite.hasNext()){
                    break;
                }
                ite.next();
            }
            for(int i = 0; i < 12 - month + 1; i++){
                if(!ite.hasNext()){
                    break;
                }
                ite.next();
            }
            end_year.add(entity);
        }
    }


    public List<SumResEntity> give_res(int type, int flag){
            if(flag == 1){
                switch (type){
                    case 0:
                        return data_sum_res;

                    case 1:
                        return data_sum_res;

                    case 2:
                        return data_sum_res_quter;

                    case 3:
                        return data_sum_res_year;

                    default:
                        return data_sum_res;

                }

            }
            else if(flag == 0){
                return data_sum_res;
            }
            return data_sum_res;
    }





}
