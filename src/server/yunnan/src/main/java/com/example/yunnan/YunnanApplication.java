package com.example.yunnan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.example.yunnan.mapper")
//@mapperScan("jetbrains://idea/navigate/reference?project=yunnan&path=src/main/java/com/example/yunnan/mapper")
public class YunnanApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunnanApplication.class, args);
    }

}
