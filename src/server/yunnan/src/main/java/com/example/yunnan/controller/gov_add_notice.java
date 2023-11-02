package com.example.yunnan.controller;

import com.example.yunnan.entity.FormDataEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class gov_add_notice {
    @RequestMapping(value="/gov_add_notice/add",method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestBody FormDataEntity formData){
        // 在这里可以访问formData对象中的属性，如formData.getText()、formData.getTextarea() 等
        // 在这里处理接收到的FormData数据
        return ResponseEntity.ok("成功接收 FormData 数据");
    }
}
