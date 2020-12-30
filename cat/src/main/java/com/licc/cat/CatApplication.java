package com.licc.cat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.licc.cat.mapper")
@EnableScheduling
public class CatApplication {


    public static void main(String[] args) {
        SpringApplication.run(CatApplication.class, args);
    }
}
