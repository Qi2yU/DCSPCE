package com.example.yunnan.service;
import com.example.yunnan.entity.SumEntity;
import com.example.yunnan.mapper.sum_databyOthers;
import com.example.yunnan.mapper.sum_databyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

public class sum_databyTime_service {
    @Autowired
    private sum_databyTime sum_databyTime;

    private List<String> User_idList;
    private List<String> Time_idList;

    String compute_Time(String time, String et){//字符串类型：20xx0901， 20xx0900，长度为8
        if(Integer.valueOf(time) >= Integer.valueOf(et)){
            return et;
        }
        else{
            if(time.charAt(7) == '1'){
                StringBuffer sb =  new StringBuffer(time);
                char month_last = time.charAt(5);
                char month_first = time.charAt(4);
                if(month_last == '2' && month_first =='1'){
                    sb.replace(7,7,String.valueOf('0'));
                    sb.replace(4,5,"01");

                }
                else{
                    if(month_last == '9'){
                        sb.replace(4,5,"10");
                        sb.replace(7,7,String.valueOf('0'));
                        return sb.toString();

                    }
                    else {
                        int ml_int = Integer.valueOf(String.valueOf(month_last));
                        ml_int++;
                        sb.replace(7,7,String.valueOf('0'));
                        sb.replace(5,5,String.valueOf(ml_int));
                        return sb.toString();
                    }
                }

            }
            else if(time.charAt(7) == '0'){
                StringBuffer sb =  new StringBuffer(time);
                return sb.replace(7,7,String.valueOf('1')).toString();
            }
        }
        return et;
    }




}
