package com.example.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lincheon
 */
@SpringBootApplication(scanBasePackages = "com.example")
@MapperScan("com.example.web.mapper")
@EnableScheduling
public class CheckAttendApiWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckAttendApiWebApplication.class, args);
    }

}
