package com.licc.cat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.licc.cat.mapper")
public class CatApplication {


    public static void main(String[] args) {
        SpringApplication.run(CatApplication.class, args);
    }
}
