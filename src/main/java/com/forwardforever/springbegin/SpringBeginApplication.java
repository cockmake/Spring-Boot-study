package com.forwardforever.springbegin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
@MapperScan("com.forwardforever.springbegin.mapper")
public class SpringBeginApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBeginApplication.class, args);
    }

}
