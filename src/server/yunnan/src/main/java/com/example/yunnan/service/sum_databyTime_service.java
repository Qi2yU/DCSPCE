package com.example.yunnan.service;
import com.example.yunnan.entity.SumEntity;
import com.example.yunnan.mapper.sum_databyTime;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.yunnan.entity.SumResEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class sum_databyTime_service {
    @Autowired
    private sum_databyTime sum_databyTime_mapper ;

    private List<List<SumEntity>> data_collection = new ArrayList<>();
    private List<SumEntity> data_collectionwithpor = new ArrayList<>();

    public List<SumResEntity> getData_sum_res() {
        return data_sum_res;
    }

    private List<SumResEntity>  data_sum_res = new ArrayList<>();

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

    public void get_datafortime(String st, String et){
        String time = new String(st);//20xx0901


        while(true){
            System.out.print(time + "\n");

            List<SumEntity>  data_signle = new ArrayList<>();
            StringBuffer time_fmt = new StringBuffer(time);//data_20xx_09_1
            time_fmt.replace(6,7,"_");//20xx09_1
            time_fmt.insert(4,"_");//20xx_09_1
            time_fmt.insert(0,"data_");//data_20xx_09_1
            data_signle =  sum_databyTime_mapper.get_data(String.valueOf(time_fmt));
            data_signle.get(0).setKind_name(time);
            data_collection.add(data_signle);
            if(Integer.valueOf(time) >= Integer.valueOf(et)){
                break;
            }
            time = compute_Time(time, et);
        }
    }

    public void sum_dataforpor(){
        for(List<SumEntity> data_single : data_collection){
            for (SumEntity data : data_single){
                data_collectionwithpor.add(data);
            }
        }
    }
    public void sum_datafortime(){
        System.out.print("汇总数据\n" );
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

    public void debug_show(){
        System.out.print("显示结果\n");
        for (SumResEntity show_data : data_sum_res){

            System.out.println(show_data.getKind_name());
        }
    }

    public List<SumResEntity> give_res(){
        return data_sum_res;
    }





}
